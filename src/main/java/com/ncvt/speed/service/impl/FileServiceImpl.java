package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.mapper.FileMapper;
import com.ncvt.speed.service.FileService;
import com.ncvt.speed.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileMapper fileMapper;

    // 根据用户id查询文件位置
    @Override
    public Result queryFileByUserId(String userId) {
        try {
            List<FileEntity> lists = fileMapper.queryFile(userId);
            if (lists.size() == 0) return Result.ok("无结果");
            return Result.ok("查询成功！",lists);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    // hash校验
    @Override
    public Result hashCheck(String userId, String hash) {
        try {
            List<FileEntity> lists = fileMapper.queryHash(userId, hash);
            if (lists.size() == 0) return Result.ok("无结果");
            return Result.ok("查询成功！",lists);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    @Override
    public Result queryFileByPath(String userId, String path) {
        try {
            FileEntity fileEntity = fileMapper.queryFileByPath(userId,path);;
            return Result.ok("查询成功！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    // 重命名
    @Override
    public Result modifyFile(FileEntity fileEntity) {
        try {
            Integer result = fileMapper.modifyFile(fileEntity);
            if (result != 1) return Result.fail(400,"修改失败！");
            return Result.ok("修改成功！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    @Override
    public Result addFile(FileEntity file, String msg) {
        try {
            int result = fileMapper.addFile(file);
            if (result == 0) return Result.fail(300,"数据库新增出现未知异常！");
            return Result.ok("合并成功，数据库记录已添加 ！",msg);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！");
        }
    }

    @Override
    public Result deleteFile(String fileName) {
        try {
            int result = fileMapper.deleteFile(fileName);
            if (result == 0){
                return Result.fail(300,"删除时出现未知异常！");
            }
            return Result.ok("删除成功！","len："+result);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    @Override
    public Result updateFile(FileEntity file) {
        try {
            int result = fileMapper.updateFile(file);
            if (result == 0) return Result.fail(300,"修改时出现未知异常！");
            return Result.ok("修改成功！","len："+result);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

}
