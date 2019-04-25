package com.imooc.o2o.dao;

import com.imooc.o2o.entity.PersonInfo;

/**
 * @Author: Alex
 * @Date: created in 21:54  2019/4/21
 * @Annotation:
 */
public interface PersonInfoDao {
    /**
     * 通过用户Id查询用户
     * @param userId
     * @return
     */
    PersonInfo queryPersonInfoById(long userId);

    /**
     * 添加用户信息
     * @param personInfo
     * @return
     */
    int insertPersonInfo(PersonInfo personInfo);

}
