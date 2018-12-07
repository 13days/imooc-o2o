package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: Alex
 * @Date: created in 17:51  2018/7/3
 * @Annotation: 主要用来解析路由并转发到相应的html中
 */
@Controller
@RequestMapping(value = "shopadmin",method = {RequestMethod.GET})
public class ShopAdminController {

    @RequestMapping(value = "productmanagement",method = RequestMethod.GET)
    public String productManagement(){
        //转发至商品管理页面
        return "shop/productmanagement";
    }

    @RequestMapping(value = "/shopoperation")
    //转发至点铺注册/编辑页面
    public String shopOperation(){
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList(){
        //转发至店铺列表页面
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement(){
        //转发至店铺管理界面
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanagement",method = RequestMethod.GET)
    public String productCategoryManage(){
        //转发至商品类别管理页面
        return "shop/productcategorymanagement";
    }

    @RequestMapping(value = "/productoperation")
    public String productOperation(){
        //转发至商品添加/编辑页面
        return "shop/productoperation";
    }

}
