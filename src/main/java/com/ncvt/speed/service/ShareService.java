package com.ncvt.speed.service;

import com.ncvt.speed.entity.ShareEntity;
import com.ncvt.speed.params.ShareParams;
import com.ncvt.speed.util.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ShareService {

    Result addShare(String userId, ShareParams shareParams, HttpServletRequest req);

    Result queryShareByUrl(String url);


}
