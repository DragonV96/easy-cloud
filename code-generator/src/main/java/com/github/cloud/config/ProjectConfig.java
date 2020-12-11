package com.github.cloud.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @date : 2020/12/11
 * @time : 23:47
 * @Description : 项目包结构及名称配置
 */
@Getter
@Component
@ConfigurationProperties(prefix = "generator.project")
public class ProjectConfig {

    /**
     * 作者
     */
    private String author;
}
