package com.seproject.req;

public class ChangeUserPasswordReq {
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;
    private String userPhone;
    public String getOldPassword(){
        return oldPassword;
    }
    public String getNewPassword(){
        return newPassword;
    }
    public String getNewPasswordConfirm(){
        return newPasswordConfirm;
    }
    public String getUserPhone(){
        return userPhone;
    }
}
