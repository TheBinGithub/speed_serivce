package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeleteCollectParams {

    @ApiModelProperty(value = "文件id列表", required = true, example = "82,89")
    private List<String> fileIdList;
    @ApiModelProperty(value = "收藏id列表", required = true, example = "82,89")
    private List<String> collectIdList;
}
