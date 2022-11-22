package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class MovementParams {

    @ApiModelProperty(value = "移动信息列表", example = "", required = true)
    private List<MovementJson> movementList;
    @ApiModelProperty(value = "新的属于id(移动到哪个)", example = "41@-.@43@-.@", required = true)
    private String newBelongId;

}
