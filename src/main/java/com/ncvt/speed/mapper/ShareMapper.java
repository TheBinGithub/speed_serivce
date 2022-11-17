package com.ncvt.speed.mapper;

import com.ncvt.speed.entity.ShareEntity;

import java.util.List;

public interface ShareMapper {

    Integer addShare(ShareEntity shareEntity);

    List<ShareEntity> queryShareByUrl(String url);

}
