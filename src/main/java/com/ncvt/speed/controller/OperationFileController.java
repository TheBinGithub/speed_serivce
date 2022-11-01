package com.ncvt.speed.controller;

import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.params.NewFolderParams;
import com.ncvt.speed.params.RecyclerParams;
import com.ncvt.speed.params.RenameParams;
import com.ncvt.speed.service.FileService;
import com.ncvt.speed.service.OperationFileService;
import com.ncvt.speed.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api(tags = "文件操作模块")
@RestController
@Slf4j
public class OperationFileController {

    @Value("${file-save-path}")
    private String path;

    @Resource
    private FileService fileService;

    @Resource
    private OperationFileService operationFileService;

    @ApiOperation(value = "查询用户所有目录(文件)")
    @GetMapping("/file/{id}")
    public Result queryFileByUserId(@PathVariable String id){
        log.info("queryFileByUserId: " + id);
        return fileService.queryFileByUserId(id);
    }

    @ApiOperation(value = "添加收藏")
    @GetMapping("/collect/{id}")
    public Result addCollect(@PathVariable String id){
        log.info("queryFileByUserId: " + id);
        return null;
    }

    @ApiOperation(value = "加入回收站")
    @PostMapping("/recycler/{userId}")
    public Result addRecycler(@PathVariable String userId, @RequestBody RecyclerParams params){
        return operationFileService.addRecycler(userId, params);
    }

    @ApiOperation(value = "查询指定目录下的目录(文件)")
    @GetMapping("/contents/{userId}/{belongId}")
    public Result queryContents(@PathVariable String userId, @PathVariable String belongId){
        log.info("queryContents: " + belongId);
        return operationFileService.queryFileByBelong(userId, belongId);
    }

    @ApiOperation(value = "新建文件夹")
    @PostMapping("/folder/{id}")
    public Result newFolder(@PathVariable String id, @RequestBody NewFolderParams newFolderParams){
        log.info("newFolder: " + newFolderParams.getFilePath());
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(newFolderParams.getFileName());
        File file = new File(path, newFolderParams.getFilePath());
        if (file.exists()) {
            return Result.fail(400, "文件夹已存在！");
        }else {
            file.mkdirs();
        }

        String s1 = newFolderParams.getFilePath().replace("\\", "@");
        String[] sList = s1.split("@");
        String belong = "";
        for (int i=0; i < sList.length - 1; i++){
            belong += sList[i]+"\\";
        }
        fileEntity.setBelong(belong);
        fileEntity.setUserId(id);
        fileEntity.setDuYou(false);
        fileEntity.setFileType("folder");
        fileEntity.setFileSize(0L);
        Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:ss");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));  // 时间戳转换成时间
        fileEntity.setUploadTime(sd);

        return fileService.addFile(fileEntity,"新建成功！");
    }

    @ApiOperation(value = "重命名")
    @PutMapping("/rename/{userId}")
    public Result rename(@PathVariable String userId, @RequestBody RenameParams renameParams){

        return operationFileService.rename(userId, renameParams);
    }

}
