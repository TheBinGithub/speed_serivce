package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
public class DownloadParams {

    @ApiModelProperty(value = "file_path", required = true, example = "[[82,89,3],[82,89,3]]")
    private List<List> pathList;

//    private List<FileJson> pathLists;


}
