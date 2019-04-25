package com.imooc.o2o.service;

import com.imooc.o2o.entity.PersonInfo;

/**
 * @Author: Alex
 * @Date: created in 14:01  2019/4/23
 * @Annotation:
 */
public interface PersonInfoService {

    /**
     * 根据用户Id获取PersonInfo信息
     * @param userId
     * @return
     */
    PersonInfo getPersonInfoById(Long userId);
}
