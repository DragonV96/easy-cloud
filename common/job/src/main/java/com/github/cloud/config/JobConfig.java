package com.github.cloud.config;

import cn.hutool.core.util.StrUtil;
import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private JobAdminConfig jobAdminConfig;

    @Autowired
    private JobExecutorConfig jobExecutorConfig;

    @Bean
    public XxlJobExecutor xxlJobExecutor() {
        log.info(" >>>>> XxlJobExecutor config init...");
        if (StrUtil.isEmpty(jobExecutorConfig.getIp())) {
            try {
                jobExecutorConfig.setIp(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException e) {
                log.error(" >>>> Failed to get host address, exception is {}", e.getLocalizedMessage());
            }
        }

        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(jobAdminConfig.getAddresses());
        xxlJobSpringExecutor.setAccessToken(jobAdminConfig.getAccessToken());
        xxlJobSpringExecutor.setAppname(jobExecutorConfig.getAppName());
        xxlJobSpringExecutor.setIp(jobExecutorConfig.getIp());
        xxlJobSpringExecutor.setPort(jobExecutorConfig.getPort());
        xxlJobSpringExecutor.setLogPath(jobExecutorConfig.getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(jobExecutorConfig.getLogRetentionDays());
        return xxlJobSpringExecutor;
    }
}
