package com.imooc.o2o.service.Impl;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Alex
 * @Date: created in 20:57  2018/11/18
 * @Annotation:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;


    /**
     * 1.若缩略图参数有值，则处理缩略图；
     * 若原先存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋值给product
     * 2.若商品详情图列表参数有值，对商品详情图片列表进行同样的操作
     * 3.将tb_product_img下面该商品原先的商品详情图记录全部删除
     * 4.更新tb_product信息
     * @param product
     * @param thumbnail
     * @param productImgHolderList
     * @return
     * @throws ProductOperationException
     */
    @Override
    @Transactional
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException {
        //空值判断
        if(product!=null && product.getShop()!=null && product.getShop().getShopId()!=null){
            product.setLastEditTime(new Date());
            if(thumbnail!=null){
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if(tempProduct.getImgAddr()!=null){
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product,thumbnail);
            }

            //如果有新存入的商品详情图时，则现将原先的删除，并添加新的图片
            if(productImgHolderList!=null&&productImgHolderList.size()>0){
                deleteProductImgList(product.getProductId());
                addProductImgList(product,productImgHolderList);
            }

            try{
                //更新商品信息
                int effectedNum = productDao.updateProduct(product);
                if(effectedNum<=0){
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS,product);
            }catch (Exception e){
                throw new ProductOperationException("更新商品信息失败:--"+e.toString());
            }
        }else{
            return new ProductExecution(ProductStateEnum.NULL_PARAMETER);
        }
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    /**
     * 分四步
     * 1、处理缩略图，获取缩略图信息相对路径并赋值给product
     * 2、往tb_product写入商品信息，获取productId
     * 3、结合productId批量处理商品详情图
     * 4、将商品详情图列表批量插入tb_product_img中
     */
    @Override
    @Transactional
    public ProductExecution addProduct(Product product,
                                       ImageHolder thumbnail,
                                       List<ImageHolder> productImgHolderList) throws ProductOperationException {
        //1、空值判断
        if(product != null && product.getShop() != null && product.getShop().getShopId() != null){
            //设置商品默认属性
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            //默认上架状态
            product.setEnableStatus(1);
            //若商品缩略图不为空则添加
            if(thumbnail != null){
                addThumbnail(product,thumbnail);
            }

            try{
                //创建商品信息
                int effectedNum = productDao.insertProduct(product);
                if(effectedNum<=0){
                    throw new ProductOperationException("创建商品失败");
                }
            }catch (Exception e ){
                throw new ProductOperationException("创建商品失败：--"+e.toString());
            }

            //若商品详情图不为空
            if(productImgHolderList!=null && productImgHolderList.size()>0){
                addProductImgList(product,productImgHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS,product);
        }else{
            //传参为空则返回空值信息
            return new ProductExecution(ProductStateEnum.NULL_PARAMETER);

        }
    }


    /**
     * 添加缩略图
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product,ImageHolder thumbnail){
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail,dest);
        product.setImgAddr(thumbnailAddr);
    }

    /**
     * 批量进行图片操作
     * @param product
     * @param productImageHolderList
     */
    private void  addProductImgList(Product product,List<ImageHolder> productImageHolderList){
        //获取图片存储路径，存放到相应店铺的文件夹底下
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        //遍历一次图片去处理，并添加进productImg实体类里
        for(ImageHolder productImgHolder:productImageHolderList){
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder,dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);

            //如果确实有图片需要添加，就执行批量添加操作
            if(productImgList.size()>0){
                try{
                    int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                    if(effectedNum<=0){
                        throw new ProductOperationException("创建商品详情图片失败");
                    }
                }catch (Exception e){
                    throw new ProductOperationException("创建商品详情图片失败:---"+e.toString());
                }
            }
        }
    }

    private void deleteProductImgList(long productId){
        //根据productId获取原先的图片
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        //删除原来的图片
        for(ProductImg productImg:productImgList){
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        //删除数据库里原有图片的信息
        productImgDao.deleteProductImgByProductId(productId);
    }

}
