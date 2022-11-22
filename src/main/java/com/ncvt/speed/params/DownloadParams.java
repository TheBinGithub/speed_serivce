package com.ncvt.speed.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Data
public class DownloadParams {

    @ApiModelProperty(value = "file_path", required = true, example = "[[82,89,3],[82,89,3]]")
    private List<List> pathList;

//    public static List<JSONObject> setP(){
//        List<JSONObject> pathLists = new ArrayList<>();
//        try {
//            JSONObject j = new JSONObject();
//            j.put("filePath","");
//            j.put("fileName","");
//            j.put("fileType","");
//            pathLists.add(j);
//            return pathLists;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return pathLists;
//    }
//
//    @ApiModelProperty(value = "file_path", required = true, example = "[82,89]")
//    private List<JSONObject> pathLists = setP();

}
