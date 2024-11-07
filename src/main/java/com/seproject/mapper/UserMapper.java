package com.seproject.mapper;

import com.seproject.pojo.CommonUser;
import com.seproject.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/*
    在这里设置与操作数据库的方法（仅声明），具体的实现类通过mybatis创建
     */
public interface UserMapper {

    @Select("select * from user where userPhone = #{userPhone}")
    List<CommonUser> selectUserByPhone(String userPhone);

    @Select("select * from user where userName = #{userName}")
    List<CommonUser> selectUserByName(String userName);

    @Insert("INSERT INTO user (userID, userPassword, userType, userName, userPhone, registerTime) " +
            "VALUES (#{userID}, #{userPassword}, #{userType}, #{userName}, #{userPhone}, #{registerTime})")
    void insertUser(CommonUser user);

    @Select("SELECT COUNT(*) FROM user")
    int getUserCount();

    @Update("UPDATE user SET userName = #{userName} WHERE userPhone = #{userPhone}")
    void changeUserName(String userName,String userPhone);

    @Update("UPDATE user SET userPassword = #{userPassword} WHERE userPhone = #{userPhone}")
    void changeUserPassword(String userPassword,String userPhone);
}
