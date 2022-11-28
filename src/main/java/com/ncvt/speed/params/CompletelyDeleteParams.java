package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CompletelyDeleteParams {

    @ApiModelProperty(value = "文件id列表(fileId)", required = true, example = "82,89")
    private List<String> fList;

    @ApiModelProperty(value = "删除id列表(deleteId)", required = true, example = "82,89")
    private List<String> dList;

    @ApiModelProperty(value = "文件属于列表(belongId)", required = true, example = "82,89")
    private List<String> bList;

}
