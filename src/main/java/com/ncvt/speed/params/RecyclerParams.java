package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecyclerParams {

    @ApiModelProperty(value = "文件(目录)id", example = "85", required = true)
    private String fileId;
    @ApiModelProperty(value = "类型(文件夹传folder，其他随意)", example = "folder", required = true)
    private String type;


}
