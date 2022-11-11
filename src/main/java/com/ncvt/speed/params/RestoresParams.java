package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RestoresParams {

    @ApiModelProperty(required = true, value = "文件id(用数组)", example = "[82,91]")
    private List<String> fileIds;

}
