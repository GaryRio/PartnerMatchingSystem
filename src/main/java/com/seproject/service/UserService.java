package com.seproject.service;

import com.seproject.pojo.CommonUser;
import com.seproject.req.*;

import java.util.List;

/*
    在这里设置Service需要实现的方法
     */
public interface UserService {
    int register(UserRegisterReq req);
    CommonUser login_password(UserLoginReq_password req);
    CommonUser login_code(UserLoginReq_code req);
    int sendVerificationCode(SendVerificationCodeReq req);
    int changeUserName(ChangeUserNameReq req);
    int changeUserPassword(ChangeUserPasswordReq req);
    List<CommonUser> getUserInfo(UserInfoGetReq req);
    int recoverUserPassword(recoverUserPasswordReq req);

}
