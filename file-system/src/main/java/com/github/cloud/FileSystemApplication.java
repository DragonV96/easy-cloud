package com.github.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : glw
 * @date : 2020/11/24
 * @time : 22:33
 * @Description : 文件服务
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FileSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileSystemApplication.class, args);
    }

}
