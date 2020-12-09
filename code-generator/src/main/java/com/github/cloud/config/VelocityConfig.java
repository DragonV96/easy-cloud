package com.github.cloud.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.app.Velocity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import java.util.Properties;

/**
 * @author : glw
 * @date : 2020/12/9
 * @time : 22:19
 * @Description : Velocity 引擎初始化配置
 */
@Slf4j
public class VelocityConfig implements CommandLineRunner {

    private static final String UTF8 = "UTF-8";

    @Override
    public void run(String... args) throws Exception {
        Properties p = new Properties();
        // 加载classpath目录下的vm文件
        p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // 定义字符集
        p.setProperty(Velocity.ENCODING_DEFAULT, UTF8);
        p.setProperty(Velocity.OUTPUT_ENCODING, UTF8);
        // 初始化Velocity引擎，指定配置Properties
        Velocity.init(p);
        log.info(" >>>>> 初始化 Velocity 引擎");
    }
}
