package com.xxl.job.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : glw
 * @datetime : 2021/3/26 12:54
 * @Description : 分布式任务调度启动类
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DistributedJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedJobApplication.class, args);
    }
}
