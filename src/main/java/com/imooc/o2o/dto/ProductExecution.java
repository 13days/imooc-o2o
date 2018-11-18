package com.imooc.o2o.dto;

import com.imooc.o2o.entity.Product;

import java.util.List;

/**
 * @Author: Alex
 * @Date: created in 21:32  2018/10/2
 * @Annotation:
 */
public class ProductExecution {
    //结果状态
    private int state;
    //标识状态
    private String stateInfo;
    //操作的product(增删改商品的时候用)
    private Product product;
    //获取product列表（查询商品列表时使用）
    private List<Product> productList;

    public ProductExecution() {

    }

    //失败的构造器
    /*public ProductExecution(ProductStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateUInfo();
    }*/
}
