package com.imooc.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.CodeUtil;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Alex
 * @Date: created in 19:58  2018/11/20
 * @Annotation:
 */
@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {
    @Autowired
    private ProductService productService;

    //支持上传商品详情图的最大数量
    private static final int IMAGEMAXCOUNT = 6;

    @RequestMapping(value = "/addproduct",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addProduct(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        //验证码校验
        if(!CodeUtil.checkVerifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入正确的验证码");
            return modelMap;
        }

        //接受前端参数变量的初始化，包括商品，缩略图，详情图列表实体类
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        String productStr = HttpServletRequestUtil.getString(request,"productStr");
        MultipartHttpServletRequest multipartRequest = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
        //接收商品缩略图
        CommonsMultipartFile thumbnailFile = null;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        try{
            //若请求中存在文件流，则取出相关的文件(包含缩略图和详情图)
            if(multipartResolver.isMultipart(request)){
                multipartRequest = (MultipartHttpServletRequest)request;

                thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");

                thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(),thumbnailFile.getInputStream());

                //取出缩略图并构建List<ImageHolder>对象，最多支持六张图片上传
                for(int i = 0;i<IMAGEMAXCOUNT;i++){
                    CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest.getFile("productImg"+i);
                    if(productImgFile!=null){
                        //若取出的第i个详情图片的文件流不为空，则将其加入详情图列表
                        ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),productImgFile.getInputStream());
                        productImgList.add(productImg);
                    }else{
                        //若取出的第i个详情图片的文件流为空，则终止循环
                        break;
                    }
                }
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg","上传的图片不能为空!");
                return modelMap;
            }
        } catch (IOException e) {
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }

        try{
            //尝试转换前端传过来的表单String流并将其转换成Product实体类
            product = mapper.readValue(productStr,Product.class); //JSON操作相关
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }

        //若Product信息，缩略图以及详情图列表均为非空，则开始进行商品添加操作
        if(product != null && thumbnail != null && productImgList.size()>0){
            try{
                //从session中获取当前店铺的Id并赋值给product，1减少对前端数据的依赖
                Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
                Shop shop = new Shop();
                shop.setShopId(currentShop.getShopId());
                product.setShop(shop);
                //执行添加操作
                ProductExecution pe = productService.addProduct(product,thumbnail,productImgList);
                if(pe.getState() == ProductStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            }catch (ProductOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入商品信息");
        }
        return modelMap;
    }

}
