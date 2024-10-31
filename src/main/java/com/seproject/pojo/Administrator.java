package com.seproject.pojo;

//管理员类Administrator，继承基类User
public class Administrator extends User {
//    extends from User
//    private int userID;
//    private String userPassword;
//    private int userType;
    public Administrator() {
    }

    public Administrator(int userID, String userPassword, int userType) {
        super(userID, userPassword, userType);
    }
}
