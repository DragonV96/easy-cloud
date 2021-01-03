package com.github.cloud.handler;

import com.github.cloud.config.*;
import com.github.cloud.service.GeneratorService;
import com.github.cloud.util.VelocityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @datetime : 2020/12/18 00:28
 * @description : 代码生成处理器
 */
@Component
public class GeneratorHandler implements CommandLineRunner {

    @Autowired
    private PackageConfig packageConfig;

    @Autowired
    private ProjectConfig projectConfig;

    @Autowired
    private SuffixConfig suffixConfig;

    @Autowired
    private SwitchConfig switchConfig;

    @Autowired
    private TableConfig tableConfig;

    @Autowired
    private GeneratorService generatorService;

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        // 生成代码
        VelocityUtil.initVelocity();
        for (TableConfig.AllowTable item : tableConfig.getAllowTables()) {
            generatorService.outputCode(projectConfig, packageConfig, switchConfig, suffixConfig, item);
        }
        // 关闭程序
        context.close();
    }
}
