package com.imooc.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @Author: Alex
 * @Date: created in 14:03  2019/4/26
 * @Annotation:
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    //需要加密的字段数组
    private String[] encryptPropNames = {"jdbc.username","jdbc.password"};

    /**
     * 对关键属性进行转换
     * @param propertyName
     * @param propertyValue
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if(isEncryptProp(propertyName)){
            //对加密字段进行解密工作
            String decryptValue = DESUtil.getDecryptString(propertyValue);
            return decryptValue;
        }else{
            return propertyValue;
        }
    }


    /**
     * 该属性是否已经加密
     * @param propertyName
     * @return
     */
    private boolean isEncryptProp(String propertyName){
        //若等于需要加密的的field， 则进行加密
        for(String encryptpropertyName:encryptPropNames){
            if(encryptpropertyName.equals(propertyName)){
                return true;
            }
        }
        return false;
    }

}
