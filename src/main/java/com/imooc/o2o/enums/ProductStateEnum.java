package com.imooc.o2o.enums;

/**
 * @Author: Alex
 * @Date: created in 20:33  2018/11/18
 * @Annotation:
 */
public enum ProductStateEnum {
    SUCCESS(1, "操作成功"),
    INNER_ERROR(-1001, "操作失败"),
    NULL_PARAMETER(-1002, "缺少参数");

    private int state;
    private String stateInfo;

    /**
     * 私有构造函数,禁止外部初始化改变定义的常量
     * @param state
     * @param stateInfo
     */
    private ProductStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    /**
     * 定义换成pulic static 暴漏给外部,通过state获取ShopStateEnum
     * values()获取全部的enum常量
     * @param state
     * @return
     */
    public static ProductStateEnum stateOf(int state) {
        for (ProductStateEnum stateEnum : values()) {
            if(stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }

}
