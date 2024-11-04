package com.seproject.resp;

import com.seproject.pojo.Member;

import java.util.List;

public class MemberShowInfoRespData extends RespData{
    private List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
