package com.seproject.controller;

import com.seproject.pojo.CommonUser;
import com.seproject.req.UserLoginReq_code;
import com.seproject.req.UserRegisterReq;
import com.seproject.req.SendVerificationCodeReq;
import com.seproject.req.ChangeUserNameReq;
import com.seproject.req.UserLoginReq_password;
import com.seproject.req.ChangeUserPasswordReq;
import com.seproject.req.UserInfoGetReq;
import com.seproject.req.recoverUserPasswordReq;

import com.seproject.resp.UserRegisterRespData;
import com.seproject.resp.UserLoginRespData;
import com.seproject.resp.Result;
import com.seproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RequestMapping("/user")
public class UserController {
    /*
    控制层：实现与前端对接的API
     */
    @Autowired
    UserService userService;


    @ResponseBody
    @PostMapping(path = "register")
    private Result register(@RequestBody UserRegisterReq req) {

        UserRegisterRespData data = new UserRegisterRespData();
        int res = userService.register(req);
        if (res == 0) {
            return Result.success();
        }

        //若失败，需要根据错误类型，返回对应的错误提示信息
        switch (res) {
            case -1:
                data.setMsg("手机号已被注册");
                break;
            case -2:
                data.setMsg("两次密码不一致");
                break;
        }
        return Result.error(data);
    }

    @PostMapping(path = "login/password") // 手机号密码登录
    public Result login_password(@RequestBody UserLoginReq_password req) {
        UserLoginRespData data = new UserLoginRespData();
        // 方便起见，返回的token就是userPhone
        CommonUser user = userService.login_password(req);
        if (user!=null) {

            return Result.success(user); //返回用户登陆的token
        } else {
            data.setMsg("手机号或密码错误");
            return Result.error(data);
        }
    }

    @PostMapping(path = "login/code")  // 手机号验证码登录
    public Result login_code(@RequestBody UserLoginReq_code req) {
        UserLoginRespData data = new UserLoginRespData();
        // 方便起见，返回的token就是userPhone
        CommonUser user = userService.login_code(req);
        if (user!=null) {

            return Result.success(user); //返回用户登陆的token
        } else {
            data.setMsg("手机号或验证码错误");
            return Result.error(data);
        }
    }

    @PostMapping(path = "sendVerificationCode")
    public Result sendVerificationCode(@RequestBody SendVerificationCodeReq req) {
        int success = userService.sendVerificationCode(req);
        if (success == 0) {
            return Result.success();
        }
        else if(success==-1) {
            return Result.error("手机号码未注册");
        }
        else{
            return Result.error("验证码发送失败");
        }
    }

    @PostMapping(path = "changeUserName")
    public Result changeUserName(@RequestBody ChangeUserNameReq req) {
        int success = userService.changeUserName(req);
        if (success == 0) {
            return Result.success();
        }
        else{
            return Result.error("更新用户名失败");
        }

    }

    @PostMapping(path = "changeUserPassword")
    public Result changeUserPassword(@RequestBody ChangeUserPasswordReq req) {
        int success = userService.changeUserPassword(req);
        if (success == 0) {
            return Result.success();
        }
        else if(success==-1){
            return Result.error("旧密码错误");
        }
        else{
            return Result.error("两次密码不一致");
        }

    }

    @PostMapping(path = "getUserInfo")
    public List<CommonUser> getUserInfo(@RequestBody UserInfoGetReq req) {
        return userService.getUserInfo(req);
    }

    @PostMapping(path = "recoverUserPassword")
    public Result recoverUserPassword(@RequestBody recoverUserPasswordReq req) {
        int success = userService.recoverUserPassword(req);
        if (success == 0) {
            return Result.success();
        }
        else if(success==-1){
            return Result.error("手机号未注册");
        }
        else if(success==-2){
            return Result.error("验证码错误");
        }
        else{
            return Result.error("两次密码不一致");
        }
    }


}
