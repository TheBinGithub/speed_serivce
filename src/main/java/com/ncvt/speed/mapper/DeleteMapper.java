package com.ncvt.speed.mapper;

import com.ncvt.speed.entity.DeleteEntity;

import java.util.List;

public interface DeleteMapper {

    // 单个添加
    Integer addDelete(DeleteEntity deleteEntity);

    // 批量添加
    Integer addDeleteList(List<DeleteEntity> lists);

    Integer dDelete(String fileId);

    List<DeleteEntity> queryRecyclerByUserId(String userId);

}
