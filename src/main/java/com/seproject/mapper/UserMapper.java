package com.seproject.mapper;

import com.seproject.pojo.CommonUser;
import com.seproject.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
    在这里设置与操作数据库的方法（仅声明），具体的实现类通过mybatis创建
     */
@Mapper
public interface UserMapper {

    @Select("select * from user where userPhone = #{userPhone}")
    List<CommonUser> selectUserByPhone(String userPhone);

}
