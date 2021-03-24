package com.github.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @date : 2020/12/11
 * @time : 23:47
 * @Description : 数据生成器配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "data")
public class ProjectConfig {

    /**
     * 数据量（默认1000w）
     */
    private Integer volume;

    /**
     * 插入数据时的合并数据量（默认2w，较优）
     */
    private Integer merge;
}
