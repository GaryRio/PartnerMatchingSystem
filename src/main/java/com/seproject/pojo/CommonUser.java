package com.seproject.pojo;

import java.util.Date;

//普通用户类CommonUser，继承基类User
public class CommonUser extends User {
    private String userName;
    private String userPhone;
    private Date registerTime;
//    extends from User
//    private int userID;
//    private String userPassword;
//    private int userType;

    public CommonUser() {
    }

    public CommonUser(int userID, String userPassword, int userType, String userName, String userPhone, Date registerTime) {
        super(userID, userPassword, userType);
        this.userName = userName;
        this.userPhone = userPhone;
        this.registerTime = registerTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}
