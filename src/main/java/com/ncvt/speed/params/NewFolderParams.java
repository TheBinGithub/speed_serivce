package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewFolderParams {

    @ApiModelProperty(value = "属于id", example = "2@-.@2", required = true)
    private String belongId;
    @ApiModelProperty(value = "目录名称", example = "新建文件夹", required = true)
    private String fileName;

}
