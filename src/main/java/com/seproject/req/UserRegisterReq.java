package com.seproject.req;

import java.util.Date;

//用户注册需要的所有数据，包括前端传递的，以及需要后端设置的
public class UserRegisterReq {
    private int userID;
    private String userPassword;
    private String userPasswordConfirm;
    private int userType;
    private String userName;
    private String userPhone;
    private Date registerTime;
    private String userCode; //验证码

    public UserRegisterReq() {
    }

    public UserRegisterReq(int userID, String userPassword, int userType, String userName, String userPhone, Date registerTime) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userType = userType;
        this.userName = userName;
        this.userPhone = userPhone;
        this.registerTime = registerTime;
    }

    public String getUserCode() {   return userCode;}

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPasswordConfirm() {
        return userPasswordConfirm;
    }

    public void setUserPasswordConfirm(String userPasswordConfirm) {
        this.userPasswordConfirm = userPasswordConfirm;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
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
