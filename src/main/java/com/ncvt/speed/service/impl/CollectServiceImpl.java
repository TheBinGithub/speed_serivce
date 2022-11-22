package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.CollectEntity;
import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.mapper.CollectMapper;
import com.ncvt.speed.mapper.FileMapper;
import com.ncvt.speed.service.CollectService;
import com.ncvt.speed.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;

    @Resource
    private FileMapper fileMapper;

    // 添加收藏
    @Override
    public Result addCollect(String userId, List<String> fileIdList) {
        try {
            List<CollectEntity> list = new ArrayList<>();
            for (String s : fileIdList){
                CollectEntity collectEntity = new CollectEntity();
                collectEntity.setUserId(userId);
                collectEntity.setFileId(s);
                list.add(collectEntity);
            }
            int cResult = collectMapper.addCollect(list);
            int fResult = fileMapper.modifyFileByDuyou(list);
            if (cResult != fileIdList.size() || fResult != cResult) return Result.fail("添加收藏出现未知异常！");
            return Result.ok("添加成功！","添加了"+cResult+"条记录");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // 查询收藏
    @Override
    public Result qeuryCollect(String userId) {
        try {
            List<FileEntity> lists = fileMapper.queryFileByDuyou(userId);
            if (lists.size() == 0) return Result.ok(404,"无记录");
            return Result.ok("查询收藏成功！",lists);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("服务端异常！",e.getMessage());
        }
    }
}
