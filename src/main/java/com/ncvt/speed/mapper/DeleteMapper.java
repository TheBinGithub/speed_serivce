package com.ncvt.speed.mapper;

import com.ncvt.speed.entity.DeleteEntity;

import java.util.List;

public interface DeleteMapper {

    // 单个添加
    Integer addDelete(DeleteEntity deleteEntity);

    // 批量添加
    Integer addDeleteList(List<DeleteEntity> lists);

    // 恢复文件
    Integer restoresDelete(List<String> fileIds);

    Integer dDelete(String fileId);

    List<DeleteEntity> queryRecyclerByUserId(String userId);

    // 根据deleteId批量删除
    Integer deleteById(List<String> dList);

}
