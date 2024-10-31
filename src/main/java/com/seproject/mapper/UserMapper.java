package com.seproject.mapper;

import com.seproject.pojo.CommonUser;
import com.seproject.pojo.User;

import java.util.List;

/*
    在这里设置与操作数据库的方法（仅声明），具体的实现类通过mybatis创建
     */
public interface UserMapper {
    List<CommonUser> selectUserByPhone(String userPhone);

}
