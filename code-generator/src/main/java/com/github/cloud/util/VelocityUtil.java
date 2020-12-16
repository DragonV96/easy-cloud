package com.github.cloud.util;

import com.github.cloud.config.*;
import com.github.cloud.constant.Constant;
import com.github.cloud.constant.TemplateConstant;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @author : glw
 * @date : 2020/12/11
 * @time : 1:01
 * @Description : velocity 模板引擎工具类
 */
public class VelocityUtil {

    public static void initVelocity() {
        Properties p = new Properties();
        // 加载classpath目录下的vm文件
        p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // 定义字符集
        p.setProperty(Velocity.ENCODING_DEFAULT, Constant.UTF8);
        p.setProperty(Velocity.OUTPUT_ENCODING, Constant.UTF8);
        // 初始化Velocity引擎，指定配置Properties
        Velocity.init(p);
    }

    /**
     * 项目包结构及名称配置
     * @param packageConfig
     * @return
     */
    public static VelocityContext buildContext(ProjectConfig projectConfig, PackageConfig packageConfig, SwitchConfig switchConfig, SuffixConfig suffixConfig, TableConfig tableConfig) {
        VelocityContext velocityContext = new VelocityContext();

        // 项目开关配置
        velocityContext.put("enableEmail", switchConfig.getEnableEmail());
        velocityContext.put("enableSwagger", switchConfig.getEnableSwagger());
        velocityContext.put("enableShiro", switchConfig.getEnableShiro());

        // 项目信息配置
        velocityContext.put("author", projectConfig.getAuthor());
        velocityContext.put("email", projectConfig.getEmail());

        // 项目类后缀配置
        velocityContext.put("requestSuffix", suffixConfig.getRequestSuffix());
        velocityContext.put("responseSuffix", suffixConfig.getResponseSuffix());

        // 项目包结构及名称配置
        velocityContext.put("rootPackageName", packageConfig.getRootPackageName());
        velocityContext.put("modulePackageName", packageConfig.getModulePackageName());
        velocityContext.put("controllerPackageName", packageConfig.getControllerPackageName());
        velocityContext.put("servicePackageName", packageConfig.getServicePackageName());
        velocityContext.put("serviceImplPackageName", packageConfig.getServiceImplPackageName());
        velocityContext.put("dtoPackageName", packageConfig.getDtoPackageName());
        velocityContext.put("requestPackageName", packageConfig.getRequestPackageName());
        velocityContext.put("responsePackageName", packageConfig.getResponsePackageName());

        return velocityContext;
    }

    /**
     * 获取模板列表
     * @return
     */
    public static List<String> getTemplateList() {
        List<String> templateList = new ArrayList<>(TemplateConstant.TEMPLATES.length);
        Collections.addAll(templateList, TemplateConstant.TEMPLATES);
        return templateList;
    }

    public static void generatorCode(ProjectConfig projectConfig, PackageConfig packageConfig, SwitchConfig switchConfig, SuffixConfig suffixConfig, TableConfig tableConfig) {
        initVelocity();
        VelocityContext velocityContext = buildContext(projectConfig, packageConfig, switchConfig, suffixConfig, tableConfig);
        List<String> templateList = getTemplateList();

        for (String template : templateList) {
            // 生成模板
            StringWriter sw = new StringWriter();
            Template resultTemplate = Velocity.getTemplate(template, Constant.UTF8);
            resultTemplate.merge(velocityContext, sw);
            // TODO
        }

    }
}
