package com.github.cloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @datetime : 2021/4/25 23:06
 * @Description : xxl-job 任务调度管理端配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "xxl.job.admin")
public class JobAdminConfig {

    /**
     * 管理服务端地址
     */
    private String addresses;

    /**
     * 授权码
     */
    private String accessToken;
}
