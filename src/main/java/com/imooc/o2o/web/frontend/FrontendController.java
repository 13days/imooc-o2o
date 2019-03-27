package com.imooc.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: Alex
 * @Date: created in 9:26  2018/12/7
 * @Annotation:
 */
@Controller
@RequestMapping("/frontend")
public class FrontendController {

    /**
     * 商品详情页路由
     * @return
     */
    @RequestMapping(value = "/productdetail",method = RequestMethod.GET)
    private String shopProductDetail(){
        return "frontend/productdetail";
    }

    /**
     * 店铺详情页路由
     * @return
     */
    @RequestMapping(value = "/shopdetail",method = RequestMethod.GET)
    private String showShopDetail(){
        return "frontend/shopdetail";
    }

    /**
     * 商品列表页路由
     * @return
     */
    @RequestMapping(value = "/shoplist",method = RequestMethod.GET)
    private String showShopList(){
        return "frontend/shoplist";
    }


    /**
     * 首页路由
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    private String index(){
        return "frontend/index";
    }

}
