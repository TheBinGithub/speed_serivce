package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ContentsParams {

    @ApiModelProperty(required = true, example = "2/我的图片")
    private String belong;
}
