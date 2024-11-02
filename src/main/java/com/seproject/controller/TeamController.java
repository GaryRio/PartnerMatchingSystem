package com.seproject.controller;

import com.seproject.req.TeamAuditReq;
import com.seproject.req.TeamCreateReq;
import com.seproject.req.TeamUpdateReq;
import com.seproject.resp.Result;
import com.seproject.resp.TeamAuditRespData;
import com.seproject.resp.TeamCreateRespData;
import com.seproject.resp.TeamUpdateRespData;
import com.seproject.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;

    @ResponseBody
    @PostMapping(path = "create")
    private Result create(@RequestBody TeamCreateReq req) {
        int res = teamService.create(req);
        //创建成功，不需要其他信息
        if (res == 0) return Result.success();

        //失败的话，需要返回错误信息
        TeamCreateRespData data = new TeamCreateRespData();
        switch(res) {
            case -1:
                data.setMsg("队伍名称已存在");
                break;
            case -9:
                data.setMsg("队伍数据存储失败");
                break;
        }
        return Result.error(data);
    }

    @ResponseBody
    @PostMapping(path = "update")
    private Result update(@RequestBody TeamUpdateReq req) {
        int res = teamService.update(req);
        //修改成功，不需要其他信息
        if (res == 0) return Result.success();

        //失败的话，需要返回错误信息
        TeamUpdateRespData data = new TeamUpdateRespData();
        switch(res) {
            case -1:
                data.setMsg("队伍名称已存在");
                break;
            case -9:
                data.setMsg("队伍数据更新存储失败");
                break;
        }
        return Result.error(data);
    }

    @ResponseBody
    @PostMapping(path = "audit")
    private Result audit(@RequestBody TeamAuditReq req) {
        int res = teamService.audit(req);
        if (res == 0) return Result.success();

        TeamAuditRespData data = new TeamAuditRespData();
        switch(res) {
            case -1:
                data.setMsg("队伍名称已存在");
                break;
            case -9:
                data.setMsg("队伍数据更新存储失败");
                break;
        }
        return Result.error(data);
    }



}
