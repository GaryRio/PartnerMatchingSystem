package com.seproject.mapper;

import com.seproject.pojo.Team;
import com.seproject.req.TeamCreateReq;
import com.seproject.req.TeamUpdateReq;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeamMapper {

    @Select("select * from team where teamName = #{teamName}")
    List<Team> selectTeamByTeamName(String teamName);

    @Select("select * from team_audit where teamName = #{teamName}")
    List<Team> selectTeamAuditByTeamName(String teamName);

    @Insert("insert into team (leaderID, teamName, headSculpturePath, teamDescription, maxMember, teamPassword, encryption, teamAudit) " +
            "values(#{leaderID}, #{teamName}, #{headSculpturePath}, #{teamDescription}, #{maxMember}, #{teamPassword}, #{encryption}, #{teamAudit})")
    int insertTeam(TeamCreateReq req);

    @Insert("insert into team_audit (teamID, leaderID, teamName, headSculpturePath, teamDescription, maxMember, teamPassword, encryption, teamAudit) " +
            "values(#{teamID}, #{leaderID}, #{teamName}, #{headSculpturePath}, #{teamDescription}, #{maxMember}, #{teamPassword}, #{encryption}, #{teamAudit})")
    int insertTeamAudit(TeamUpdateReq req);

    @Select("select * from team where teamID = #{teamID}")
    List<Team> selectTeamByTeamID(int teamID);

    @Select("select * from team_audit where teamID = #{teamID}")
    List<Team> selectTeamAuditByTeamID(int teamID);

    @Update("update team set teamName = #{teamName} where teamID = #{teamID}")
    int updateTeamNameByTeamID(int teamID, String teamName);

    @Update("update team set headSculpturePath = #{headSculpturePath} where teamID = #{teamID}")
    int updateTeamHeadSculpturePathByTeamID(int teamID, String headSculpturePath);

    @Update("update team set maxMember = #{maxMember} where teamID = #{teamID}")
    int updateTeamMaxMemberByTeamID(int teamID, int maxMember);

    @Update("update team set encryption = #{encryption} where teamID = #{teamID}")
    int updateTeamEncryptionByTeamID(int teamID, boolean encryption);

    @Update("update team set teamPassword = #{teamPassword} where teamID = #{teamID}")
    int updateTeamPasswordByTeamID(int teamID, String teamPassword);

    @Update("update team set teamDescription = #{teamDescription} where teamID = #{teamID}")
    int updateTeamDescriptionByTeamID(int teamID, String teamDescription);

    @Update("update team set teamAudit = #{teamAudit} where teamID = #{teamID}")
    int updateTeamAuditByTeamID(int teamID, int teamAudit);

    @Delete("delete from team_audit where teamID = #{teamID}")
    int deleteTeamAuditByTeamID(int teamID);
}
