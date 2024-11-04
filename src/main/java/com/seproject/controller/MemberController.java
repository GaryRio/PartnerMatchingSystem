package com.seproject.controller;

import com.seproject.pojo.Member;
import com.seproject.req.MemberAddReq;
import com.seproject.req.MemberRemoveReq;
import com.seproject.req.MemberShowInfoReq;
import com.seproject.req.TeamCreateReq;
import com.seproject.resp.MemberAddRespData;
import com.seproject.resp.MemberShowInfoRespData;
import com.seproject.resp.Result;
import com.seproject.resp.TeamCreateRespData;
import com.seproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @ResponseBody
    @PostMapping(path = "jointeam")
    private Result joinTeam(@RequestBody MemberAddReq req) {
        int res = memberService.joinTeam(req);
        //创建成功，不需要其他信息
        if (res == 0) return Result.success();

        //失败的话，需要返回错误信息
        MemberAddRespData data = new MemberAddRespData();
        switch(res) {
            case -1:
                data.setMsg("目标队伍不存在");
                break;
            case -2:
                data.setMsg("目标队伍已满");
                break;
            case -9:
                data.setMsg("成员数据存储异常");
                break;
        }
        return Result.error(data);
    }

    @ResponseBody
    @PostMapping(path = "exitteam")
    private Result exitTeam(@RequestBody MemberRemoveReq req) {
        int res = memberService.exitTeam(req);
        //创建成功，不需要其他信息
        if (res == 0) return Result.success();

        //失败的话，需要返回错误信息
        MemberAddRespData data = new MemberAddRespData();
        switch(res) {
            case -1:
                data.setMsg("目标队伍不存在");
                break;
            case -9:
                data.setMsg("成员数据删除异常");
                break;
        }
        return Result.error(data);
    }

    @ResponseBody
    @PostMapping(path = "showinfo")
    private Result showInfo(@RequestBody MemberShowInfoReq req) {
        MemberShowInfoRespData data = new MemberShowInfoRespData();
        //查找成员信息
        List<Member> members = memberService.findAllMembersOfTeam(req);
        if(members.size() == 0) {
            data.setMsg("队伍不存在");
            return Result.error(data);
        }

        data.setMembers(members);
        return Result.success(data);
    }
}
