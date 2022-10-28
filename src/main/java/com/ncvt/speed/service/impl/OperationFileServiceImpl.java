package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.mapper.FileMapper;
import com.ncvt.speed.params.RenameParams;
import com.ncvt.speed.service.OperationFileService;
import com.ncvt.speed.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
public class OperationFileServiceImpl implements OperationFileService {

    @Value("${file-save-path}")
    private String path;

    @Resource
    private FileMapper fileMapper;

    // 重命名
    @Override
    public Result rename(String userId, RenameParams param) {
        try {
            File oldFile = new File(path,param.getOldFilePath());
            File newFile = new File(path,param.getNewFilePath());
            boolean result = oldFile.renameTo(newFile);
            FileEntity fileEntity = fileMapper.queryFileByPath(userId,oldFile.getPath());
            if (fileEntity == null) return Result.fail("数据库无记录！");
            String s1 = param.getNewFilePath().replace("\\", "@");
            String[] s = s1.split("@");
            fileEntity.setFileName(s[s.length - 1]);
            fileEntity.setFilePath(newFile.getPath());
            Integer result1 = fileMapper.modifyFile(fileEntity);
            if (result && result1 == 1){
                return Result.ok("修改成功！");
            }
            return Result.fail("修改出现未知异常！");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    @Override
    public Result queryFileByBelong(String userId, String belong) {
        try {
            List<FileEntity> fileEntityList = fileMapper.queryFileByBelong(userId, belong);
            if (fileEntityList.size() == 0) return Result.ok(404,"数据库无数据！");
            return Result.ok("查询成功！",fileEntityList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }
}
