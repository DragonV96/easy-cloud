package com.github.cloud.handler;

import com.github.cloud.service.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @datetime : 2021/3/24 23:23
 * @Description : 数据生成处理器
 */
@Component
public class DataHandler implements CommandLineRunner {

    @Autowired
    private InsertService insertService;

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        // 生成数据
        insertService.insertUser();
        // 关闭程序
        context.close();
    }
}
