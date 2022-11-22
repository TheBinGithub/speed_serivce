package com.ncvt.speed.mapper;

import com.ncvt.speed.entity.CollectEntity;
import com.ncvt.speed.params.CollectParams;

import java.util.List;

public interface CollectMapper {

    // 添加收藏
    Integer addCollect(List<CollectEntity> lists);

}
