package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.BelongEntity;
import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.mapper.BelongMapper;
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

    @Resource
    private BelongMapper belongMapper;

    // 重命名
    @Override
    public Result rename(String userId, RenameParams param) {
        try {
            String o = param.getBelong()+param.getOldName();
            String n = param.getBelong()+param.getNewName();
            File oldFile = new File(path,o);
            File newFile = new File(path,n);
            boolean result = oldFile.renameTo(newFile);
            FileEntity fileEntity = fileMapper.queryFileByName(param.getFileId(),userId,param.getOldName());
            if (fileEntity == null) return Result.fail(404,"数据库无记录！");
            fileEntity.setFileName(param.getNewName());
            Integer result1 = fileMapper.modifyFile(fileEntity);
            Integer result2 = 99;
            if (param.getType().equals("folder")){
                BelongEntity belong = belongMapper.queryBelongByBelong(o+"\\");
                if (belong != null){
                    belong.setBelong(n+"\\");
                    result2 = belongMapper.modifyBelong(belong);
                }
            }
            System.out.println("result2:"+result2);
            if (result2 == 99){
                if (result && result1 == 1){
                    return Result.ok("修改成功！");
                }
                return Result.fail("修改出现未知异常！");
            }else {
                if (result && result1 == 1 && result2 == 1){
                    return Result.ok("修改成功！");
                }
                return Result.fail("修改belong出现未知异常！");
            }

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
