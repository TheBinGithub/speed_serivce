package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.List;

@Data
public class CollectParams {

    @ApiModelProperty(value = "添加收藏的数据列表", required = true, example = "[82,89]")
    private List<JSONObject> collectList;


}
