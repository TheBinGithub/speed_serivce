package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecyclerParams {

    @ApiModelProperty(value = "文件id", example = "85", required = true)
    private Integer fileId;
    @ApiModelProperty(value = "文件地址", example = "E:\\bishe\\file\\2\\a.txt", required = true)
    private String filePath;

}
