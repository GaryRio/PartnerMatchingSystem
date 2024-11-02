package com.seproject.service;

import com.seproject.req.TeamAuditReq;
import com.seproject.req.TeamCreateReq;
import com.seproject.req.TeamUpdateReq;
import org.springframework.stereotype.Service;

public interface TeamService {
    int create(TeamCreateReq req);

    int update(TeamUpdateReq req);

    int audit(TeamAuditReq req);
}
