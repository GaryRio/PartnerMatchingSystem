package com.seproject.req;

public class TeamAuditReq {
    private int teamID;
    private int auditType;
    private boolean approved;

    public TeamAuditReq() {
    }

    public TeamAuditReq(int teamID, int auditType, boolean approved) {
        this.teamID = teamID;
        this.auditType = auditType;
        this.approved = approved;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getAuditType() {
        return auditType;
    }

    public void setAuditType(int auditType) {
        this.auditType = auditType;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
