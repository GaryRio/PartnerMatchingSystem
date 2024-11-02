package com.seproject.req;

public class TeamCreateReq {
    private int teamID;
    private int leaderID;
    private String teamName;
    private String headSculpturePath;
    private String teamDescription;
    private int maxMember;
    private String teamPassword;
    private boolean encryption;
    private int teamAudit;

    public TeamCreateReq() {
    }

    public TeamCreateReq(int teamID, int leaderID, String teamName, String headSculpturePath, String teamDescription, int maxMember, String teamPassword, boolean encryption, int teamAudit) {
        this.teamID = teamID;
        this.leaderID = leaderID;
        this.teamName = teamName;
        this.headSculpturePath = headSculpturePath;
        this.teamDescription = teamDescription;
        this.maxMember = maxMember;
        this.teamPassword = teamPassword;
        this.encryption = encryption;
        this.teamAudit = teamAudit;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getLeaderID() {
        return leaderID;
    }

    public void setLeaderID(int leaderID) {
        this.leaderID = leaderID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getHeadSculpturePath() {
        return headSculpturePath;
    }

    public void setHeadSculpturePath(String headSculpturePath) {
        this.headSculpturePath = headSculpturePath;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }

    public int getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(int maxMember) {
        this.maxMember = maxMember;
    }

    public String getTeamPassword() {
        return teamPassword;
    }

    public void setTeamPassword(String teamPassword) {
        this.teamPassword = teamPassword;
    }

    public boolean isEncryption() {
        return encryption;
    }

    public void setEncryption(boolean encryption) {
        this.encryption = encryption;
    }

    public int getTeamAudit() {
        return teamAudit;
    }

    public void setTeamAudit(int teamAudit) {
        this.teamAudit = teamAudit;
    }
}
