package com.ncvt.speed.controller;

import com.ncvt.speed.entity.BelongEntity;
import com.ncvt.speed.entity.FileEntity;
import com.ncvt.speed.params.MovementParams;
import com.ncvt.speed.params.NewFolderParams;
import com.ncvt.speed.params.RecyclerParams;
import com.ncvt.speed.params.RenameParams;
import com.ncvt.speed.service.BelongService;
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

    @ApiOperation(value = "查询指定目录下的目录(文件)")
    @GetMapping("/contents/{userId}/{belong}")
    public Result queryContents(@PathVariable String userId, @PathVariable String belong){
        log.info("queryFileByBelong: " + belong);
        return operationFileService.queryFileByBelong(userId, belong);
    }

    @ApiOperation(value = "新建文件夹")
    @PostMapping("/folder/{id}")
    public Result newFolder(@PathVariable String id, @RequestBody NewFolderParams newFolderParams){
        log.info("newFolder: " + newFolderParams.getFileName());
        return operationFileService.addFolder(id, newFolderParams.getFileName(), newFolderParams.getBelongId());
    }

    @ApiOperation(value = "重命名")
    @PutMapping("/rename/{userId}")
    public Result rename(@PathVariable String userId, @RequestBody RenameParams renameParams){
        log.info("rename: " + renameParams.getFileId());
        return operationFileService.rename(userId, renameParams);
    }

    @ApiOperation(value = "目录(文件)移动")
    @PutMapping("/movement/{userId}")
    public Result movement(@PathVariable String userId, @RequestBody MovementParams movementParams){
        log.info("movement: " + movementParams.getFileId());
        return operationFileService.movement(movementParams);
    }

}
