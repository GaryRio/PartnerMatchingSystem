package com.seproject.req;

public class MemberShowInfoReq {
    private int teamID;

    public MemberShowInfoReq() {
    }

    public MemberShowInfoReq(int teamID) {
        this.teamID = teamID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
}
