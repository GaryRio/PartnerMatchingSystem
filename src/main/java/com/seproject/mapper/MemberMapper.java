package com.seproject.mapper;

import com.seproject.pojo.CommonUser;
import com.seproject.pojo.Member;
import com.seproject.req.MemberAddReq;
import com.seproject.req.MemberRemoveReq;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Select("select userID from member where teamID = #{teamID}")
    List<Integer> selectAllMembersIDOfTeamByTeamID(int teamID);

    @Insert("insert into member (teamID, userID, memberType) " +
            "values (#{teamID}, #{userID}, #{memberType})")
    int insertMember(MemberAddReq req);

    @Update("update member set memberType = #{newType} where teamID = #{teamID} and userID = #{userID}")
    int updateMemberType(int teamID, int userID, int newType);

    @Delete("delete from member where teamID = #{teamID} and userID = #{userID}")
    int deleteMember(MemberRemoveReq req);

    @Select("select * from member where teamID = #{teamID}")
    List<Member> selectAllMembersOfTeamByTeamID(int teamID);
}
