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
    @ApiModelProperty(required = true, value = "belong", example = "2/我的图片")
    private String belong;
    @ApiModelProperty(required = true, value = "type", example = "txt")
    private String type;
    @ApiModelProperty(required = true, value = "64", example = "64")
    private String fileId;
}
