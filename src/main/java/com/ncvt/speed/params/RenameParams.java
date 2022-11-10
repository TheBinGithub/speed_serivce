package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RenameParams {

    @ApiModelProperty(required = true, value = "旧的目录或文件名", example = "旧图片名.jpg")
//    value–字段说明
//    name–重写属性名字
//    dataType–重写属性类型
//    required–是否必填
//    example–举例说明
//    hidden–隐藏
    private String oldName;
    @ApiModelProperty(required = true, value = "新的目录或文件名", example = "新图片名.jpg")
    private String newName;
    @ApiModelProperty(required = true, value = "属于id(暂时没用到,随意上传)", example = "2@-.@")
    private String belongId;
    @ApiModelProperty(required = true, value = "类型", example = "txt")
    private String type;
    @ApiModelProperty(required = true, value = "文件id", example = "64")
    private String fileId;
    @ApiModelProperty(required = true, value = "文件夹本身的属于id", example = "4")
    private String folderBelongId;
}
