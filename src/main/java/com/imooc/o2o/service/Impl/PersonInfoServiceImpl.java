package com.imooc.o2o.service.Impl;

import com.imooc.o2o.dao.PersonInfoDao;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Alex
 * @Date: created in 14:02  2019/4/23
 * @Annotation:
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public PersonInfo getPersonInfoById(Long userId) {
        return personInfoDao.queryPersonInfoById(userId);
    }
}
