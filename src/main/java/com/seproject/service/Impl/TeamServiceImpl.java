package com.seproject.service.Impl;

import com.seproject.mapper.MemberMapper;
import com.seproject.mapper.TeamMapper;
import com.seproject.pojo.Team;
import com.seproject.req.*;
import com.seproject.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private static final int TEAM_AUDIT_NORMAL = 0; //正常
    private static final int TEAM_AUDIT_CREATE = 1; //创建（待审核）
    private static final int TEAM_AUDIT_UPDATE = 2; //更新（待审核）
    private static final int TEAM_AUDIT_END = 9; //销毁（创建失败/删除）
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    MemberMapper memberMapper;


    @Override
    public int create(TeamCreateReq req) {
        //检查队伍名称是否重复
        String teamName = req.getTeamName();
        List<Team> teams = teamMapper.selectTeamByTeamName(teamName);
        if (teams.size() != 0) {
            return -1; //队伍名称重复
        }

        req.setTeamAudit(TEAM_AUDIT_CREATE);
        //存储队伍数据到数据库中，返回值是数据库的effected rows
        int res = teamMapper.insertTeam(req);
        if (res != 1) return -9; //存储出错

        //获取teamID
        teams = teamMapper.selectTeamByTeamName(teamName);
        Team team = teams.get(0);
        int teamID = team.getTeamID();
        //在member中也要添加相应信息
        MemberAddReq addReq = new MemberAddReq(req.getLeaderID(), teamID, MemberServiceImpl.MEMBER_TYPE_LEADER);
        res = memberMapper.insertMember(addReq);

        //存储成功
        if(res == 1) return 0;
        return -9;
    }

    @Override
    public int update(TeamUpdateReq req) {
        //检查队伍名称是否重复
        String teamName = req.getTeamName();
        List<Team> teams = teamMapper.selectTeamByTeamName(teamName);
        List<Team> audit_teams = teamMapper.selectTeamAuditByTeamName(teamName);
        if (teams.size() != 0 || audit_teams.size() != 0) {
            return -1; //队伍名称重复
        }
        //因为修改队伍信息需要审核，所以真正的修改是在审核中完成，这里需要将新数据存入待审核表中
        //设置队伍状态为更新待审核
        req.setTeamAudit(TEAM_AUDIT_UPDATE);
        //直接将新的所有数据插入到team的待审核表（team_audit）中
        int res = teamMapper.insertTeamAudit(req);
        if (res != 1) {
            return -9; //修改出错
        }

        res = teamMapper.updateTeamAuditByTeamID(req.getTeamID(), TEAM_AUDIT_UPDATE);
        if (res != 1) {
            return -9; //修改出错
        }
        return 0;
    }

    @Override
    public int updateLeader(TeamUpdateLeaderReq req) {
        int teamID = req.getTeamID();
        int currentLeaderID = req.getCurrentLeaderID();
        int newLeaderID = req.getNewLeaderID();

        int res = memberMapper.updateMemberType(teamID, currentLeaderID, MemberServiceImpl.MEMBER_TYPE_COMMON);
        if (res != 1) {
            return -9; //数据库更新错误
        }
        res = memberMapper.updateMemberType(teamID, newLeaderID, MemberServiceImpl.MEMBER_TYPE_LEADER);
        if (res != 1) {
            return -9; //数据库更新错误
        }
        res = teamMapper.updateTeamLeaderIDByTeamID(teamID, newLeaderID);
        if (res != 1) {
            return -9; //数据库更新错误
        }
        return 0;
    }

    @Override
    public Team getTeamInfo(TeamShowInfoReq req) {
        int teamID = req.getTeamID();
        //查找队伍信息
        List<Team> teams = teamMapper.selectTeamByTeamID(teamID);
        if(teams.size() == 0)
            return null;
        //成功则返回Team对象
        return teams.get(0);
    }

    @Override
    public List<String> findAllMembers(TeamShowInfoReq req) {
        int teamID = req.getTeamID();
        //先查找到所有成员的ID
        List<Integer> memberIDs = memberMapper.selectAllMembersIDOfTeamByTeamID(teamID);

        //TODO：将ID转换为名称
        List<String> members = new ArrayList<>();

        return members;
    }

    @Override
    public int delete(TeamDeleteReq req) {
        int teamID = req.getTeamID();
        //直接删除team表中的信息即可，member表中的信息会因为teamID的外键约束级联删除
        int res = teamMapper.deleteTeamByTeamID(teamID);
        if(res != 1) {
            return -9; //数据库更新错误
        }
        return 0;
    }

    @Override
    public int audit(TeamAuditReq req) {
        int auditType = req.getAuditType();
        int teamID = req.getTeamID();

        if (auditType == 1) {
            // 创建审核，直接在源数据库中修改
            if (req.isApproved()) {
                //通过
                int res = teamMapper.updateTeamAuditByTeamID(teamID, TEAM_AUDIT_NORMAL);
                if (res != 1) {
                    return -9; //修改出错
                }
            }
            else {
                //未通过
                int res = teamMapper.updateTeamAuditByTeamID(teamID, TEAM_AUDIT_END);
                if (res != 1) {
                    return -9; //修改出错
                }
            }
        }
        else if (auditType == 2) {
            //修改审核
            if (req.isApproved()) {
                //通过，修改数据
                List<Team> new_teams = teamMapper.selectTeamAuditByTeamID(teamID);
                Team new_team = new_teams.get(0); //新的数据
                List<Team> teams = teamMapper.selectTeamByTeamID(teamID);
                Team team = teams.get(0); //原数据

                //新的数据
                String new_headSculpturePath = new_team.getHeadSculpturePath();
                String new_teamName = new_team.getTeamName();
                int new_maxMember = new_team.getMaxMember();
                boolean new_encryption = new_team.isEncryption();
                String new_teamPassword = new_team.getTeamPassword();
                String new_teamDescription = new_team.getTeamDescription();
                //判断数据是否发生修改
                if (!team.getTeamName().equals(new_teamName)) {
                    int res = teamMapper.updateTeamNameByTeamID(teamID, new_teamName);
                    if (res != 1) {
                        return -9; //修改出错
                    }
                }
                if (!team.getHeadSculpturePath().equals(new_headSculpturePath)) {
                    int res = teamMapper.updateTeamHeadSculpturePathByTeamID(teamID, new_headSculpturePath);
                    if (res != 1) {
                        return -9; //修改出错
                    }
                }
                if (team.getMaxMember() != new_maxMember) {
                    int res = teamMapper.updateTeamMaxMemberByTeamID(teamID, new_maxMember);
                    if (res != 1) {
                        return -9; //修改出错
                    }
                }
                if (team.isEncryption() != new_encryption) {
                    int res = teamMapper.updateTeamEncryptionByTeamID(teamID, new_encryption);
                    if (res != 1) {
                        return -9; //修改出错
                    }
                }
                if (!team.getTeamPassword().equals(new_teamPassword)) {
                    int res = teamMapper.updateTeamPasswordByTeamID(teamID, new_teamPassword);
                    if (res != 1) {
                        return -9; //修改出错
                    }
                }
                if (!team.getTeamDescription().equals(new_teamDescription)) {
                    int res = teamMapper.updateTeamDescriptionByTeamID(teamID, new_teamDescription);
                    if (res != 1) {
                        return -9; //修改出错
                    }
                }
            }

            // 无论通过与否，状态都设置正常
            int res = teamMapper.updateTeamAuditByTeamID(teamID, TEAM_AUDIT_NORMAL);
            if (res != 1) {
                return -9; //修改出错
            }
            //修改审核完成之后，需要删除待审核表中的数据
            res = teamMapper.deleteTeamAuditByTeamID(teamID);
            if (res != 1) {
                return -9; //修改出错
            }
        }
        return 0;
    }
}
