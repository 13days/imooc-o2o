package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WechatAuth;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @Author: Alex
 * @Date: created in 11:23  2019/4/22
 * @Annotation:
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WechatAuthDaoTest extends BaseTest {

    @Autowired
    private WechatAuthDao wechatAuthDao;

    @Test
    public void testAInsertWechatAuth() throws Exception{
        //新增一条微信账号
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        //给微信账号绑定用户id
        wechatAuth.setPersonInfo(personInfo);
        //随意设置openid
        wechatAuth.setOpenId("zczdy");
        wechatAuth.setCreateTime(new Date());
        int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
        assertEquals(1,effectedNum);
    }


    @Test
    public void testBQueryWechatAuthByOpenId() throws Exception{
        WechatAuth wechatAuth = wechatAuthDao.queryWechatInfoByOpenId("zczdy");
        assertEquals("Alex",wechatAuth.getPersonInfo().getName());
    }

}
