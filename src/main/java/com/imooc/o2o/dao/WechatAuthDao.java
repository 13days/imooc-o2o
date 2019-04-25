package com.imooc.o2o.dao;

import com.imooc.o2o.entity.WechatAuth;

/**
 * @Author: Alex
 * @Date: created in 22:51  2019/4/21
 * @Annotation:
 */
public interface WechatAuthDao {
    /**
     * 通过openid查询对应本平台的微信账号
     * @param openid
     * @return
     */
    WechatAuth queryWechatInfoByOpenId(String openid);

    /**
     * 添加对应本平台微信账号
     * @param wechatAuth
     * @return
     */
    int insertWechatAuth(WechatAuth wechatAuth);

}
