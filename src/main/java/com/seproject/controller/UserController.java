package com.seproject.controller;

import com.seproject.req.UserRegisterReq;
import com.seproject.resp.RegisterRespData;
import com.seproject.resp.Result;
import com.seproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

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

        int res = userService.register(req);
        //若成功，返回success（不需要传data）
        if (res == 0) return Result.success();

        //若失败，需要根据错误类型，返回对应的错误提示信息
        RegisterRespData data = new RegisterRespData();
        switch (res) {
            case -1:
                data.setMsg("手机号已被注册");
                break;
        }
        return Result.error(data);
    }
}
