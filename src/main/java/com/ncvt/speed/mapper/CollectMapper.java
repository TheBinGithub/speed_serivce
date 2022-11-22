package com.ncvt.speed.mapper;

import com.ncvt.speed.entity.CollectEntity;

import java.util.List;

public interface CollectMapper {

    // 添加收藏
    Integer addCollect(List<CollectEntity> lists);

    // 移出收藏
    Integer deleteCollect(List<String> lists);

}
