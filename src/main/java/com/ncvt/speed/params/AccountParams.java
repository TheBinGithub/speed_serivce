package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountParams {

    @ApiModelProperty(value = "用户名", example = "admin", required = true)
    private String userName;
    @ApiModelProperty(value = "密码", example = "admin123", required = true)
    private String password;
    private String salt;

}
