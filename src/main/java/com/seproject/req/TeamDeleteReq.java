package com.seproject.req;

public class TeamDeleteReq {
    private int teamID;

    public TeamDeleteReq() {
    }

    public TeamDeleteReq(int teamID) {
        this.teamID = teamID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
}
