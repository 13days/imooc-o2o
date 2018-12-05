package com.imooc.o2o.exceptions;

/**
 * @Author: Alex
 * @Date: created in 20:44  2018/11/18
 * @Annotation:
 */
public class ProductOperationException extends RuntimeException {
    public ProductOperationException(String message) {
        super(message);
    }
}
