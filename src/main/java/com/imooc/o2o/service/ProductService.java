package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exceptions.ProductOperationException;

import java.io.InputStream;
import java.util.List;

/**
 * @Author: Alex
 * @Date: created in 21:27  2018/10/2
 * @Annotation:
 */
public interface ProductService {

    /**
     * 修改商品信息及图片处理
     * @param product
     * @param thumbnail
     * @param productImgList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product,ImageHolder thumbnail,List<ImageHolder> productImgHolderList)
        throws ProductOperationException;


    /**
     * 通过商品ID查询唯一商品信息
     * @param ProductId
     * @return
     */
    Product getProductById(long ProductId);

    /**
     * 添加商品信息以及图片处理
     * @param product
     * 缩略图
     * @param thumbnail
     * 详情图
     * @param productImgList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product,
                                ImageHolder thumbnail,
                                List<ImageHolder> productImgList) throws ProductOperationException;

    //废弃 上面为重构方法
    /*ProductExecution addProduct(Product product, InputStream thumbnail,
                                   String thumbnailName, List<InputStream> productImgList,
                                   List<String> productImgNameList) throws ProductOperationException;*/
}
