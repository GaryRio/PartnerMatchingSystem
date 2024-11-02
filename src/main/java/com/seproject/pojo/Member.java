package com.seproject.pojo;

import java.util.Date;

public class Member {
    private int teamID;
    private int userID;
    private Date joinTime;
    private int memberType;

    public Member() {
    }

    public Member(int teamID, int userID, Date joinTime, int memberType) {
        this.teamID = teamID;
        this.userID = userID;
        this.joinTime = joinTime;
        this.memberType = memberType;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }
}
