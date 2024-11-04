package com.seproject.req;

public class TeamUpdateLeaderReq {
    private int teamID;
    private int currentLeaderID;
    private int newLeaderID;

    public TeamUpdateLeaderReq() {
    }

    public TeamUpdateLeaderReq(int teamID, int currentLeaderID, int newLeaderID) {
        this.teamID = teamID;
        this.currentLeaderID = currentLeaderID;
        this.newLeaderID = newLeaderID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getCurrentLeaderID() {
        return currentLeaderID;
    }

    public void setCurrentLeaderID(int currentLeaderID) {
        this.currentLeaderID = currentLeaderID;
    }

    public int getNewLeaderID() {
        return newLeaderID;
    }

    public void setNewLeaderID(int newLeaderID) {
        this.newLeaderID = newLeaderID;
    }
}
