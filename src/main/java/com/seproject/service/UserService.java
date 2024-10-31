package com.seproject.service;

import com.seproject.req.UserRegisterReq;

/*
    在这里设置Service需要实现的方法
     */
public interface UserService {
    int register(UserRegisterReq req);

}
