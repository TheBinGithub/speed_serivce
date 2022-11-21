package com.ncvt.speed.config;

import com.ncvt.speed.util.SavePath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Value("${file-save-path}")
//    String path;
    private final String path = SavePath.savePath();

//    String separator = File.separator+File.separator;  // 获取文件名称分隔符, win \ ,linux/
//    private String path = separator+"bishe"+separator+"file"+separator;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + path).setCachePeriod(0);
    }

}

