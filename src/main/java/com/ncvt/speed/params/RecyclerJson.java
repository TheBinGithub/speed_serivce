package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecyclerJson {

    @ApiModelProperty(value = "文件(目录)id列表", example = "", required = true)
    private String fileId;
    @ApiModelProperty(value = "类型列表", example = "", required = true)
    private String type;
    @ApiModelProperty(value = "cBelongId列表", example = "", required = true)
    private String cBelongId;
}
