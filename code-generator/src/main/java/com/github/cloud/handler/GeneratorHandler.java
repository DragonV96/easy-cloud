package com.github.cloud.handler;

import cn.hutool.core.io.FileUtil;
import com.github.cloud.config.*;
import com.github.cloud.constant.Constant;
import com.github.cloud.util.VelocityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.List;

/**
 * @author : glw
 * @datetime : 2020/12/16 22:48
 * @description : 代码生成器处理器
 */
@Slf4j
@Component
public class GeneratorHandler {

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

    public void outputCode(ProjectConfig projectConfig, PackageConfig packageConfig, SwitchConfig switchConfig, SuffixConfig suffixConfig, TableConfig tableConfig){
        VelocityContext velocityContext = VelocityUtil.buildContext(projectConfig, packageConfig, switchConfig, suffixConfig, tableConfig);
        List<String> templateList = VelocityUtil.getTemplateList();

        for (String template : templateList) {
            // 生成模板
            StringWriter sw = new StringWriter();
            Template resultTemplate = Velocity.getTemplate(template, Constant.UTF8);
            resultTemplate.merge(velocityContext, sw);

            FileUtil.writeUtf8String(sw.toString(), VelocityUtil.getFileName(projectConfig, packageConfig, suffixConfig, tableConfig, template));
        }
    }
}
