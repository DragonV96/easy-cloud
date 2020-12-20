package com.github.cloud.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import com.github.cloud.config.*;
import com.github.cloud.constant.Constant;
import com.github.cloud.service.DataSourceService;
import com.github.cloud.service.GeneratorService;
import com.github.cloud.util.VelocityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.sql.Connection;
import java.util.List;

/**
 * @author : glw
 * @datetime : 2020/12/16 22:48
 * @description : 代码生成业务层实现
 */
@Slf4j
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    private DataSourceService dataSourceService;

    public void outputCode(ProjectConfig projectConfig, PackageConfig packageConfig, SwitchConfig switchConfig, SuffixConfig suffixConfig, TableConfig tableConfig){
        Connection connect = null;
        try {
            connect = dataSourceService.connect();
            dataSourceService.execute(connect, ""); // TODO

            VelocityContext velocityContext = VelocityUtil.buildContext(projectConfig, packageConfig, switchConfig, suffixConfig, tableConfig);
            List<String> templateList = VelocityUtil.getTemplateList();

            for (String template : templateList) {
                // 生成模板
                StringWriter sw = new StringWriter();
                Template resultTemplate = Velocity.getTemplate(template, Constant.UTF8);
                resultTemplate.merge(velocityContext, sw);

                FileUtil.writeUtf8String(sw.toString(), VelocityUtil.getFileName(projectConfig, packageConfig, suffixConfig, tableConfig, template));
            }
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        } catch (ParseErrorException e) {
            e.printStackTrace();
        } catch (MethodInvocationException e) {
            e.printStackTrace();
        } catch (IORuntimeException e) {
            e.printStackTrace();
        } finally {
            dataSourceService.close(connect);
        }
    }
}
