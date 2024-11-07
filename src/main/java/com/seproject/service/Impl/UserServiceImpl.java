package com.seproject.service.Impl;

import com.seproject.mapper.UserMapper;
import com.seproject.pojo.CommonUser;
import com.seproject.req.*;
import com.seproject.service.UserService;
import com.seproject.service.SMSsend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;



import java.util.List;

@Service
public class UserServiceImpl implements UserService {
//    // 存储用户登录后的token
//    private static Map<String,String> tokenStore = new HashMap<>();

    @Autowired
    UserMapper userMapper; //通过该userMapper完成数据库操作

    @Override
    public int register(UserRegisterReq req) {
        String userPhone = req.getUserPhone();
        List<CommonUser> commonUsers = userMapper.selectUserByPhone(userPhone);
        if(commonUsers.size() != 0) {
            return -1; //-1表示手机号已存在
        }
        else{ // 手机号未被注册
            // 检查验证码是否正确
            Map<String, Object> codeInfo = SMSsend.verificationCodes.get(userPhone);
            if(codeInfo==null){
                // 如果不存在验证码，返回错误
                return -1;
            }
            String storedCode = (String) codeInfo.get("code");

            if(!verifyPassword(req.getUserCode(),storedCode)){
                // 如果验证码错误
                return -1;
            }

            if(!verifyPassword(req.getUserPassword(),req.getUserPasswordConfirm())){
                // 如果两次输入的密码不一致
                return -2;
            }
            //到达此处，说明注册成功

            CommonUser newUser=new CommonUser();
            newUser.setUserPhone(userPhone);
            Date currentTime = new Date();
            newUser.setRegisterTime(currentTime);
            newUser.setUserPassword(req.getUserPassword());
            newUser.setUserName(req.getUserName());
            newUser.setUserType(0); //普通用户类型为0，管理员为1
            // 不考虑用户注销的情况，用户id采用递增的生成方式生成
            newUser.setUserID(1000000+userMapper.getUserCount());
            userMapper.insertUser(newUser);
            return 0;
        }
    }

    // 手机号密码登录
    public CommonUser login_password(UserLoginReq_password req) {
        String userPhone = req.getUserPhone();
        String userPassword = req.getUserPassword();
        List<CommonUser> commonUsers = userMapper.selectUserByPhone(userPhone);
        if(commonUsers.size() == 0) {
            return null; //账号未注册
        }
        else{
            CommonUser user = commonUsers.get(0); // 手机号是唯一的，只取第一个结果

            if (verifyPassword(userPassword, user.getUserPassword())) {

                return user;
            } else {
                // 密码验证失败
                return null; // 密码错误
            }
        }
    }

    // 手机号验证码登录
    public CommonUser login_code(UserLoginReq_code req){
        String userPhone = req.getUserPhone();
        String userCode = req.getUserCode();
        List<CommonUser> commonUsers = userMapper.selectUserByPhone(userPhone);
        if(commonUsers.size() == 0) {
            return null; //账号未注册
        }
        else{

            if (SMSsend.verifyCode(userPhone,userCode)) {
                CommonUser user = commonUsers.get(0); // 手机号是唯一的，只取第一个结果
                return user;
            } else {
                // 密码验证失败
                return null; // 密码错误
            }
        }
    }


    // 请求发送验证码
    public int sendVerificationCode(SendVerificationCodeReq req){
        String userPhone = req.getUserPhone();
        List<CommonUser> commonUsers = userMapper.selectUserByPhone(userPhone);
        if(commonUsers.size() == 0) {
            return -1; // 手机号未注册
        }
        else{
            try {
                //发短信
                SendSmsResponse response = SMSsend.sendSms(userPhone);
                if(response.getCode().equals("OK")) {
                    return 0; // 短信发送成功
                }else {
                    return -2; //短信发送失败
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return -2; // 或者抛出运行时异常（如果这是不可恢复的错误）
            }
        }
    }

    public int changeUserName(ChangeUserNameReq req){
        String userPhone = req.getUserPhone();
        String userName = req.getUserName();
        // 判断userName是否合法
        try {
            // 调用Mapper方法进行用户名更新
            userMapper.changeUserName(userName, userPhone);

            // 如果更新成功，可以在这里添加一些逻辑，比如记录日志、发送通知等
            System.out.println("用户名更新成功！");
            return 0;

        } catch (Exception e) {
            // 捕获并处理异常
            // 这里可以记录异常信息到日志文件，或者将异常信息返回给前端用户等
            System.err.println("更新用户名时发生错误：" + e.getMessage());

            // 根据需要，可以进一步处理异常，比如回滚事务、抛出新的异常等
            // throw new RuntimeException("更新用户名失败", e); // 如果需要，可以重新抛出异常
            return -1;
        }
    }

    public int changeUserPassword(ChangeUserPasswordReq req){
        String oldPassword = req.getOldPassword();
        String newPassword = req.getNewPassword();
        String newPasswordConfirm = req.getNewPasswordConfirm();

        String userPhone = req.getUserPhone();
        List<CommonUser> commonUsers = userMapper.selectUserByPhone(userPhone);
        CommonUser user = commonUsers.get(0); // 手机号是唯一的，只取第一个结果

        if (!verifyPassword(oldPassword, user.getUserPassword())){
            // 输入的旧密码错误
            return -1;
        }

        if(!verifyPassword(newPassword,newPasswordConfirm)){
            return -2;  // 两次密码不一致
        }
        try {
            // 调用Mapper方法进行用户名更新
            userMapper.changeUserPassword(newPassword, userPhone);

            // 如果更新成功，可以在这里添加一些逻辑，比如记录日志、发送通知等
            System.out.println("密码更新成功！");
            return 0;

        } catch (Exception e) {
            // 捕获并处理异常
            // 这里可以记录异常信息到日志文件，或者将异常信息返回给前端用户等
            System.err.println("密码更新错误：" + e.getMessage());

            // 根据需要，可以进一步处理异常，比如回滚事务、抛出新的异常等
            // throw new RuntimeException("更新用户名失败", e); // 如果需要，可以重新抛出异常
            return -3;
        }

    }

    public List<CommonUser> getUserInfo(UserInfoGetReq req){
        String userName = req.getUserName();
        List<CommonUser> commonUsers = userMapper.selectUserByName(userName);
        if(commonUsers.size() == 0) {
            return null;
        }
        else {
            return commonUsers;
        }
    }

    public int recoverUserPassword(recoverUserPasswordReq req){
        String userPhone = req.getUserPhone();
        String userCode = req.getCode();
        String newPassword = req.getNewPassword();
        String newPasswordConfirm = req.getNewPasswordConfirm();
        List<CommonUser> commonUsers = userMapper.selectUserByPhone(userPhone);
        if(commonUsers.size() == 0) {
            return -1; // 手机号未注册
        }
        if (!SMSsend.verifyCode(userPhone,userCode)) {
            return -2;// 验证码错误
        }
        if(!verifyPassword(newPassword,newPasswordConfirm)){
            return -3;//两次密码不一致
        }
        //符合要求
        userMapper.changeUserPassword(newPassword,userPhone);
        return 0;
    }

    // 判断密码 or 验证码是否正确
    private boolean verifyPassword(String input, String correct) {
        return input.equals(correct);
    }

}