package com.imooc.o2o.service;

import com.imooc.o2o.entity.ProductCategory;

import java.util.List;

/**
 * @Author: Alex
 * @Date: created in 15:15  2018/7/25
 * @Annotation:
 */
public interface ProductCategoryService {
    /**
     * 查询某个店铺下的所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategory(long shopId);
}
