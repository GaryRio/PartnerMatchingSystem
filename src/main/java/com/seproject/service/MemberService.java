package com.seproject.service;

import com.seproject.pojo.Member;
import com.seproject.req.MemberAddReq;
import com.seproject.req.MemberRemoveReq;
import com.seproject.req.MemberShowInfoReq;

import java.util.List;

public interface MemberService {
    int joinTeam(MemberAddReq req);

    int exitTeam(MemberRemoveReq req);

    List<Member> findAllMembersOfTeam(MemberShowInfoReq req);
}
