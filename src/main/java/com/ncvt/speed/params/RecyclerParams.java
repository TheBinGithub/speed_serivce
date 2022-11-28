package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RecyclerParams {

    @ApiModelProperty(value = "加入回收站信息列表", example = "", required = true)
    List<RecyclerJson> recyclerList;

}
