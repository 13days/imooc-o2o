package com.imooc.o2o.dto;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.enums.ProductStateEnum;

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
    //商品数量
    private int count;

    //2选1
    //操作的product(增删改商品的时候用)
    private Product product;
    //获取product列表（查询商品列表时使用）
    private List<Product> productList;

    public ProductExecution() {

    }

    //失败的构造器
    public ProductExecution(ProductStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功的构造器
    public ProductExecution(ProductStateEnum stateEnum,Product product){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.product = product;
    }

    //成功的构造器
    public ProductExecution(ProductStateEnum stateEnum,List<Product> productList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productList = productList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
