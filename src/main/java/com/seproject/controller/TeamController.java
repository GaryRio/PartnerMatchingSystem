package com.seproject.controller;

import com.seproject.pojo.Team;
import com.seproject.req.*;
import com.seproject.resp.*;
import com.seproject.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping(path = "updateleader")
    private Result updateLeader(@RequestBody TeamUpdateLeaderReq req) {
        int res = teamService.updateLeader(req);
        //修改成功，不需要其他信息
        if (res == 0) return Result.success();

        //失败的话，需要返回错误信息
        TeamUpdateRespData data = new TeamUpdateRespData();
        switch(res) {
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

    @ResponseBody
    @PostMapping(path = "showinfo")
    private Result showInfo(@RequestBody TeamShowInfoReq req) {
        TeamShowInfoRespData data = new TeamShowInfoRespData();
        //查找队伍信息
        Team team = teamService.getTeamInfo(req);
        if(team == null) {
            data.setMsg("队伍不存在");
            return Result.error(data);
        }
        //查找队伍成员信息
        List<String> members = teamService.findAllMembers(req);
        if(members.size() == 0) {
            data.setMsg("队伍不存在");
            return Result.error(data);
        }

        data.setTeam(team);
        data.setMembers(members);
        return Result.success(data);
    }

    @ResponseBody
    @PostMapping(path = "delete")
    private Result delete(@RequestBody TeamDeleteReq req) {
        int res = teamService.delete(req);
        if (res == 0) return Result.success();

        TeamAuditRespData data = new TeamAuditRespData();
        switch(res) {
            case -9:
                data.setMsg("队伍数据更新失败");
                break;
        }
        return Result.error(data);
    }



}
