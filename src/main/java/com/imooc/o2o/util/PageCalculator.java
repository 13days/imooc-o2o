package com.imooc.o2o.util;

/**
 * @Author: Alex
 * @Date: created in 15:55  2018/7/18
 * @Annotation:
 */
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex,int pageSize){
        return (pageIndex>0)?(pageIndex-1)*pageSize:0;
    }
}
