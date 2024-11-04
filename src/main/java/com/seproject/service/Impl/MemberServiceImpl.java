package com.seproject.service.Impl;

import com.seproject.mapper.MemberMapper;
import com.seproject.mapper.TeamMapper;
import com.seproject.pojo.Member;
import com.seproject.pojo.Team;
import com.seproject.req.MemberAddReq;
import com.seproject.req.MemberRemoveReq;
import com.seproject.req.MemberShowInfoReq;
import com.seproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    public static final int MEMBER_TYPE_LEADER = 1; //队长
    public static final int MEMBER_TYPE_COMMON = 0; //普通成员
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    TeamMapper teamMapper;

    @Override
    public int joinTeam(MemberAddReq req) {
        //先查找到目标队伍
        int teamID = req.getTeamID();
        List<Team> teams = teamMapper.selectTeamByTeamID(teamID);
        if (teams.size() == 0) {
            return -1; //目标队伍不存在
        }
        Team team = teams.get(0);
        //目标队伍人数是否已满
        int maxMember = team.getMaxMember();
        List<Integer> memberIDs = memberMapper.selectAllMembersIDOfTeamByTeamID(teamID);
        if(maxMember == memberIDs.size()) {
            return -2; //目标队伍已满
        }
        //可以加入，设置加入时间（由MySQL完成）
        //设置成员类型
        req.setMemberType(MEMBER_TYPE_COMMON);
        int res = memberMapper.insertMember(req);
        if (res != 1) {
            return -9; //数据库存储错误
        }
        return 0;
    }

    @Override
    public int exitTeam(MemberRemoveReq req) {
        int teamID = req.getTeamID();
        List<Team> teams = teamMapper.selectTeamByTeamID(teamID);
        if (teams.size() == 0) {
            return -1; //目标队伍不存在
        }

        int res = memberMapper.deleteMember(req);
        if (res != 1) {
            return -9; //数据库删除错误
        }
        return 0;
    }

    @Override
    public List<Member> findAllMembersOfTeam(MemberShowInfoReq req) {
        int teamID = req.getTeamID();
        List<Member> members = memberMapper.selectAllMembersOfTeamByTeamID(teamID);
        return members;
    }
}
