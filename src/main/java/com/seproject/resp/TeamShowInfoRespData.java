package com.seproject.resp;

import com.seproject.pojo.Team;

import java.util.List;


public class TeamShowInfoRespData extends RespData{
    private Team team;
    private List<String> members;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
