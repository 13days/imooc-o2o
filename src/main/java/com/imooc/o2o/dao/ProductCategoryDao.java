package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;

import java.util.List;

/**
 * @Author: Alex
 * @Date: created in 14:49  2018/7/25
 * @Annotation:
 */
public interface ProductCategoryDao {
    /**
     *
     * @param shopId
     * @return List<ProductCategoryDao>
     */
    List<ProductCategory> queryProductCategoryList(long shopId);
}
