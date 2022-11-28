package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SePasswordParams {

    @ApiModelProperty(required = true, value = "二级密码", example = "123")
    private String sePassword;

}
