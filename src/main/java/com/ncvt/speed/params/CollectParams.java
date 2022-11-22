package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CollectParams {

    @ApiModelProperty(value = "收藏数据列表", required = true, example = "[82,89]")
    private List<CollectJson> CollectList;


}
