package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PasswordParams {

    @ApiModelProperty(value = "旧密码", example = "admin", required = true)
    private String oldPassword;
    @ApiModelProperty(value = "新密码", example = "admin123", required = true)
    private String newPassword;

}
