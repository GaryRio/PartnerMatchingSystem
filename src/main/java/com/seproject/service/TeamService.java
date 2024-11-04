package com.seproject.service;

import com.seproject.pojo.Team;
import com.seproject.req.*;

import java.util.List;

public interface TeamService {
    int create(TeamCreateReq req);

    int update(TeamUpdateReq req);

    int audit(TeamAuditReq req);

    int updateLeader(TeamUpdateLeaderReq req);

    Team getTeamInfo(TeamShowInfoReq req);

    List<String> findAllMembers(TeamShowInfoReq req);

    int delete(TeamDeleteReq req);
}
