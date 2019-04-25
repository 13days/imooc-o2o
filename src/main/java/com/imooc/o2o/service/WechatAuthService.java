package com.imooc.o2o.service;

import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.WechatAuth;
import com.imooc.o2o.exceptions.WechatAuthOperationException;

/**
 * @Author: Alex
 * @Date: created in 14:31  2019/4/22
 * @Annotation:
 */
public interface WechatAuthService {
    /**
     * 通过openId查找平台对应的微信账号
     * @param openId
     * @return
     */
    WechatAuth getWechatAuthByOpenId(String openId);

    /**
     * 注册本平台的微信账号
     * @param wechatAuth
     * @return
     * @throws WechatAuthOperationException
     */
    WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;
}
