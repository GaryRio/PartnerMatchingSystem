package com.seproject.req;

public class recoverUserPasswordReq {
    private String code; //验证码
    private String newPassword;
    private String newPasswordConfirm;
    private String userPhone;
    public String getCode(){
        return code;
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
