package com.seproject.req;

public class TeamShowInfoReq {
    private int teamID;

    public TeamShowInfoReq() {
    }

    public TeamShowInfoReq(int teamID) {
        this.teamID = teamID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
}
