package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Product;

/**
 * @Author: Alex
 * @Date: created in 19:58  2018/9/25
 * @Annotation:
 */
public interface ProductDao {
    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);
}
