package com.imooc.o2o.exceptions;

/**
 * @Author: Alex
 * @Date: created in 22:22  2018/7/28
 * @Annotation:
 */
public class ProductCategoryOperationException extends RuntimeException{
    public ProductCategoryOperationException(String message) {
        super(message);
    }
}
