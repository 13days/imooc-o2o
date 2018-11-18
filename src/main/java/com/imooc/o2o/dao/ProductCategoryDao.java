package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 批量新增商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     *删除指定商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * 两个参数myBatis识别不了，所以需要@Param这个注解来标明参数名
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,@Param("shopId") long shopId);
}
