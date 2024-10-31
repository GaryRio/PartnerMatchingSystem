package com.seproject.service.Impl;

import com.seproject.mapper.UserMapper;
import com.seproject.pojo.CommonUser;
import com.seproject.req.UserRegisterReq;
import com.seproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper; //通过该userMapper完成数据库操作

    @Override
    public int register(UserRegisterReq req) {
        String userPhone = req.getUserPhone();
        List<CommonUser> commonUsers = userMapper.selectUserByPhone(userPhone);
        if(commonUsers.size() != 0) {
            return -1; //-1表示手机号已存在
        }
        //TODO：其他逻辑判断
        return 0;
    }
}
