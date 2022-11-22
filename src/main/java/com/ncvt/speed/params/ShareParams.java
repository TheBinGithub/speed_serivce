package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShareParams {

    @ApiModelProperty(required = true, value = "文件id", example = "82")
    private String fileId;
    @ApiModelProperty(required = true, value = "这是啥?", example = "82")
    private String command;
    @ApiModelProperty(required = true, value = "类型", example = "folder")
    private String type;
    @ApiModelProperty(required = true, value = "用户名", example = "old_xis")
    private String userName;

}
