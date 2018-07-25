package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: Alex
 * @Date: created in 17:51  2018/7/3
 * @Annotation:
 */
@Controller
@RequestMapping(value = "shopadmin",method = {RequestMethod.GET})
public class ShopAdminController {
    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList(){
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement(){
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanagement",method = RequestMethod.GET)
    public String productCategoryManage(){
        return "shop/productcategorymanagement";
    }
}
