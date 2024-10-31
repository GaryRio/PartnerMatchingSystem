package com.seproject.service.Impl;

import com.seproject.mapper.UserMapper;
import com.seproject.req.UserRegisterReq;
import com.seproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper; //通过该userMapper完成数据库操作

    @Override
    public int register(UserRegisterReq req) {

        return 0;
    }
}
