package com.seproject.pojo;

//User基类
public class User {
    private int userID;
    private String userPassword;
    private int userType;

    public User() {
    }

    public User(int userID, String userPassword, int userType) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userType = userType;
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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
