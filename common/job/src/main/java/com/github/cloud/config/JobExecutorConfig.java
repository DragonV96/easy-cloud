package com.github.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @datetime : 2021/4/25 23:50
 * @Description : xxl-job 任务执行端配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "xxl.job.executor")
public class JobExecutorConfig {

    /**
     * 服务名
     */
    private String appName;

    /**
     * 服务ip地址
     */
    private String ip;

    /**
     * 服务端口
     */
    private Integer port;

    /**
     * 日志地址
     */
    private String logPath;

    /**
     * 日志保存天数
     */
    private Integer logRetentionDays;
}
