package com.imooc.o2o.exceptions;

/**
 * @Author: Alex
 * @Date: created in 15:23  2019/4/22
 * @Annotation:
 */
public class WechatAuthOperationException extends RuntimeException {
    public WechatAuthOperationException(String msg){
        super(msg);
    }
}
