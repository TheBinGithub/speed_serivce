package com.ncvt.speed.mapper;

import com.ncvt.speed.entity.CollectEntity;
import com.ncvt.speed.entity.DeleteEntity;
import com.ncvt.speed.entity.FileEntity;

import java.util.List;

public interface FileMapper {

    // 查询用户文件
    List<FileEntity> queryFile(String userId);

    // 查询hash
    List<FileEntity> queryHash(String hash);

    // 根据id和fileName查询
    FileEntity queryFileByName(String fileId,String userId, String fileName);

    // 根据belongId和fileName查询
    List<FileEntity> queryFileByFileName(String belongId, String fileName);

    // 根据id和belongId查询
    List<FileEntity> queryFileByBelong(String userId, String belongId);

    // 根据belongId右侧模糊查询
    List<FileEntity> queryFileLikeBelongId(String BelongId);

    // 修改记录
    Integer modifyFile(FileEntity fileEntity);

    // 查询用户回收站
    List<FileEntity> queryD(String userId);

    // 根据fileId批量修改(批量逻辑删除)
    Integer modifyFileByFileId(List<DeleteEntity> lists);

    // 恢复文件
    Integer restoresFile(List<String> fileIds);

    // 移动
    Integer movement(List<FileEntity> lists);

    // 逻辑删除
    Integer logicalDeletionFile(String fileId, Long deleteId);

    // 添加
    Integer addFile(FileEntity file);

    // 添加收藏
    Integer modifyFileByDuyou(List<CollectEntity> lists);

    // 查询收藏
    List<FileEntity> queryFileByDuyou(String userId);

    // 移出收藏
    Integer modifyFileByDuyouDelete(List<String> lists);

    // 删除
    Integer deleteFile(String FileName);

    // 修改
    Integer updateFile(FileEntity file);

    // 查询回收站所有数据
    List<FileEntity> queryAllDelete(Long time);

    // 根据fileId批量删除
    Integer deleteFileById(List<String> idList);

    // 根据fileId批量彻底逻辑删除
    Integer completelyDelete(List<String> fileIdList);

    // 查询彻底删除的数据
    List<FileEntity> queryFileByD();

}
