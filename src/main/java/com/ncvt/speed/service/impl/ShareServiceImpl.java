package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.ShareEntity;
import com.ncvt.speed.mapper.ShareMapper;
import com.ncvt.speed.params.ShareParams;
import com.ncvt.speed.service.ShareService;
import com.ncvt.speed.util.Md5;
import com.ncvt.speed.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
@Slf4j
public class ShareServiceImpl implements ShareService {

    @Resource
    private ShareMapper shareMapper;

    // 创建分享
    @Override
    public Result addShare(String userId, ShareParams shareParams, HttpServletRequest req) {
        try {
            String salt = UUID.randomUUID().toString().toUpperCase();
            String shareUrl = Md5.getMd5Password(shareParams.getFileId(),salt);
            ShareEntity shareEntity = new ShareEntity();
            shareEntity.setUserId(userId);
            shareEntity.setFileId(shareParams.getFileId());
            shareEntity.setCommand(shareParams.getCommand());
            shareEntity.setShareType(shareParams.getType());
            shareEntity.setUserName(shareParams.getUserName());
            shareEntity.setShareUrl(shareUrl);
            shareEntity.setShareTime(System.currentTimeMillis());  // 获取当前时间戳
            int result = shareMapper.addShare(shareEntity);
            if (result != 1) return Result.fail("新增分享出现未知异常！");
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/s/"+shareUrl;
            return Result.ok("新增分享成功！",url);
        }catch (Exception e){
            log.info("异常："+e);
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    @Override
    public Result queryShareByUrl(String url) {
        return null;
    }

}
