package com.ncvt.speed.service.impl;

import com.ncvt.speed.entity.CollectEntity;
import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.mapper.CollectMapper;
import com.ncvt.speed.mapper.FileMapper;
import com.ncvt.speed.params.CollectJson;
import com.ncvt.speed.service.CollectService;
import com.ncvt.speed.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;

    @Resource
    private FileMapper fileMapper;

    // 添加收藏
    @Override
    public Result addCollect(String userId, List<CollectJson> collectList) {
        try {
            List<CollectEntity> list = new ArrayList<>();
            for (CollectJson c : collectList){
                CollectEntity collectEntity = new CollectEntity();
                collectEntity.setUserId(userId);
                collectEntity.setFileId(c.getFileId());
                list.add(collectEntity);
                if (c.getFileType().equals("folder")){
                    List<FileEntity> fileList = fileMapper.queryFileLikeBelongId(c.getCBelongId());
                    for (FileEntity f :fileList){
                        CollectEntity collectEntity1 = new CollectEntity();
                        collectEntity1.setUserId(userId);
                        collectEntity1.setFileId(f.getFileId());
                        list.add(collectEntity1);
                    }
                }
            }
            int cResult = collectMapper.addCollect(list);
            int fResult = fileMapper.modifyFileByDuyou(list);
            if (cResult != list.size() || fResult != cResult) return Result.fail("添加收藏出现未知异常！");
            return Result.ok("添加成功！","添加了"+cResult+"条记录");
        }catch (Exception e){
            log.info("异常："+e);
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
            log.info("异常："+e);
            return Result.fail("服务端异常！",e.getMessage());
        }
    }

    // 移出收藏
    @Override
    public Result deleteCollect(List<String> fileIdList, List<String> collectIdList) {
        try {
            if (fileIdList.size() != collectIdList.size()) return Result.fail("参数不匹配！");
            int fResult = fileMapper.modifyFileByDuyouDelete(fileIdList);
            int cResult = collectMapper.deleteCollect(collectIdList);
            if ((fResult + cResult) != (fileIdList.size()*2)) return Result.fail("移出收藏出现未知异常！");
            return Result.ok("移出收藏成功！","修改了"+fResult+"条记录！");
        }catch (Exception e){
            log.info("异常："+e);
            return Result.fail("服务端异常！", e.getMessage());
        }
    }

}
