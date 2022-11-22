package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MovementJson {

    @ApiModelProperty(value = "文件id", example = "85", required = true)
    private String fileId;
    @ApiModelProperty(value = "类型", example = "folder(文件夹folder,其他随意)", required = true)
    private String type;
    @ApiModelProperty(value = "旧的属于id(原来是什么就丢什么过来)", example = "12@-.@14", required = true)
    private String oldBelongId;
    @ApiModelProperty(value = "folderBelongId", example = "如果是文件夹则把folderBelongId丢过来，文件则随意传", required = true)
    private String folderBelongId;

}
