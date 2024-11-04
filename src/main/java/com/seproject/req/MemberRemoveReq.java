package com.seproject.req;

public class MemberRemoveReq {
    private int teamID;
    private int userID;

    public MemberRemoveReq() {
    }

    public MemberRemoveReq(int teamID, int userID) {
        this.teamID = teamID;
        this.userID = userID;
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
}
