package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewFolderParams {

    @ApiModelProperty(value = "地址，从id开始。", example = "2/我的图片/新建文件夹", required = true)
    private String filePath;
    @ApiModelProperty(value = "目录名称", example = "新建文件夹", required = true)
    private String fileName;

}
