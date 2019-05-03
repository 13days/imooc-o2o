package com.imooc.o2o.service;

import com.imooc.o2o.dto.LocalAuthExecution;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.exceptions.LocalAuthOperationException;

/**
 * @Author: Alex
 * @Date: created in 15:42  2019/4/28
 * @Annotation:
 */
public interface LocalAuthService {
    /**
     * 通过账号密码获取平台信息
     * @param userName
     * @param passWord
     * @return
     */
    LocalAuth getLocalAuthByUsernameAndPwd(String userName, String passWord);

    /**
     * 通过userId获取平台账号信息
     * @param userId
     * @return
     */
    LocalAuth getLocalAuthByUserId(long userId);

    /**
     * 绑定微信，生成平台专属账号
     * @param localAuth
     * @return
     * @throws LocalAuthOperationException
     */
    LocalAuthExecution bindLocalAuth(LocalAuth localAuth)throws LocalAuthOperationException;

    /**
     * 修改平台账号的登陆密码
     * @param userId
     * @param username
     * @param password
     * @param newPassword
     * @return
     * @throws LocalAuthOperationException
     */
    LocalAuthExecution modifyLocalAuth(Long userId,String username,String password,String newPassword)
        throws LocalAuthOperationException;

}
