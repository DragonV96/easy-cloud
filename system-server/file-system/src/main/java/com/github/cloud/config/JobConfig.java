package com.github.cloud.config;

import cn.hutool.core.util.StrUtil;
import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author : glw
 * @datetime : 2021/3/26 13:28
 * @Description : 任务调度配置
 */
@Slf4j
@Configuration
public class JobConfig {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.app-name}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private Integer port;

    @Value("${xxl.job.executor.log-path}")
    private String logPath;

    @Value("${xxl.job.executor.log-retention-days}")
    private Integer logRetentionDays;

    @Value("${xxl.job.access-token}")
    private String accessToken;

    @Bean
    public XxlJobExecutor xxlJobExecutor() {
        log.info(" >>>>> XxlJobExecutor config init...");
        if (StrUtil.isEmpty(ip)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(" >>>> Failed to get host address, exception is {}", e.getLocalizedMessage());
            }
        }

        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        return xxlJobSpringExecutor;
    }
}
