package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RestoresParams {

    @ApiModelProperty(required = true, value = "文件id", example = "82")
    private String fileId;

}
