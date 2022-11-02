package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MovementParams {

    @ApiModelProperty(value = "文件id", example = "85", required = true)
    private String fileId;
    @ApiModelProperty(value = "属于id(移动到哪个)", example = "12", required = true)
    private String belongId;

}
