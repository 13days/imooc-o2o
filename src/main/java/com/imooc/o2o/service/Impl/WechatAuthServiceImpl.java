package com.imooc.o2o.service.Impl;

import com.imooc.o2o.dao.PersonInfoDao;
import com.imooc.o2o.dao.WechatAuthDao;
import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WechatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;
import com.imooc.o2o.exceptions.WechatAuthOperationException;
import com.imooc.o2o.service.WechatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: Alex
 * @Date: created in 15:26  2019/4/22
 * @Annotation:
 */
@Service
public class WechatAuthServiceImpl implements WechatAuthService {
    private static Logger log = LoggerFactory.getLogger(WechatAuthServiceImpl.class);

    @Autowired
    private WechatAuthDao wechatAuthDao;

    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public WechatAuth getWechatAuthByOpenId(String openId) {
        return wechatAuthDao.queryWechatInfoByOpenId(openId);
    }

    @Override
    @Transactional
    public WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException {
        //控制判断
        if(wechatAuth == null || wechatAuth.getOpenId() == null){
            return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
        }

        try{
            //设置系统时间
            wechatAuth.setCreateTime(new Date());
            //如果微信账号里夹带着用户信息并且userId为空，则认为该用户第一次使用平台（且通过微信登录),则自动创建用户信息
            if(wechatAuth.getPersonInfo()!=null && wechatAuth.getPersonInfo().getUserId()==null){
                try{
                    wechatAuth.getPersonInfo().setCreateTime(new Date());
                    wechatAuth.getPersonInfo().setEnableStatus(1);
                    PersonInfo personInfo = wechatAuth.getPersonInfo();
                    int effectedNum = personInfoDao.insertPersonInfo(personInfo);
                    wechatAuth.setPersonInfo(personInfo);
                    if(effectedNum <= 0){
                        throw new WechatAuthOperationException("添加用户信息失败");
                    }
                }catch (Exception e){
                    log.error("insertPersonInfo error:"+e.toString());
                    throw new WechatAuthOperationException("insertPersonInfo error:"+e.getMessage());
                }
            }

                //创建属于本平台的微信账号
                int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
                if(effectedNum <= 0){
                    throw new WechatAuthOperationException("创建账号失败");
                }else{
                    return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS,wechatAuth);
                }

        }catch (Exception e){
            log.error("insertPersonInfo error:"+e.toString());
            throw new WechatAuthOperationException("insertPersonInfo error:"+e.getMessage());
        }
    }
}
