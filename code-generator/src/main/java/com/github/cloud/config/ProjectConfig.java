package com.github.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @date : 2020/12/11
 * @time : 23:47
 * @Description : 项目信息配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "generator.project")
public class ProjectConfig {

    /**
     * 项目名称 TODO 未完
     */
    private String projectName;

    /**
     * 作者
     */
    private String author;

    /**
     * 作者邮箱
     */
    private String email;

    /**
     * 生成路径
     */
    private String path;

    /**
     * 版本号 TODO 未完
     */
    private String version;
}
