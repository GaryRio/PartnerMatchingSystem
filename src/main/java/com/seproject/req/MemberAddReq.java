package com.seproject.req;

import java.util.Date;

public class MemberAddReq {
    private int userID;
    private int teamID;
    private int memberType;

    public MemberAddReq() {
    }

    public MemberAddReq(int userID, int teamID, int memberType) {
        this.userID = userID;
        this.teamID = teamID;
        this.memberType = memberType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }
}
