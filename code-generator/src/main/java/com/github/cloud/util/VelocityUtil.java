package com.github.cloud.util;

import cn.hutool.core.util.StrUtil;
import com.github.cloud.config.*;
import com.github.cloud.constant.Constant;
import com.github.cloud.constant.TemplateConstant;
import com.github.cloud.enums.TemplateEnum;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.util.StringUtils;

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

    /**
     * 获取生成文件名
     * @param projectConfig
     * @param packageConfig
     * @param suffixConfig
     * @param tableConfig
     * @param template
     * @return
     */
    public static String getFileName(ProjectConfig projectConfig, PackageConfig packageConfig, SuffixConfig suffixConfig, TableConfig tableConfig, String template) {
        StringBuilder filePath = new StringBuilder();

        // 公共包路径
        filePath.append(projectConfig.getPath())
                .append(projectConfig.getProjectName())
                .append(Constant.SPLIT)
                .append(Constant.PROJECT_PATH)
                .append(Constant.SPLIT)
                .append(packageConfig.getRootPackageName().replace(Constant.DOT, Constant.SPLIT))
                .append(Constant.SPLIT)
                .append(packageConfig.getModulePackageName())
                .append(Constant.SPLIT);

        if (TemplateEnum.ADD.getName().equals(template) ||
                TemplateEnum.UPDATE.getName().equals(template) ||
                TemplateEnum.PAGE.getName().equals(template)) {
            // 请求对象
            filePath.append(packageConfig.getDtoPackageName())
                    .append(packageConfig.getRequestPackageName())
                    .append(template.replace(Constant.VM, ""))
                    // TODO append 表名
                    .append(suffixConfig.getRequestSuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (TemplateEnum.RESPONSE.getName().equals(template)) {
            // 响应对象
            filePath.append(packageConfig.getDtoPackageName())
                    .append(packageConfig.getResponsePackageName())
                    .append(template.replace(Constant.VM, ""))
                    // TODO append 表名
                    .append(suffixConfig.getResponseSuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (TemplateEnum.ENTITY.getName().equals(template)) {
            // 数据库实体类
            filePath.append(packageConfig.getDtoPackageName())
                    .append(packageConfig.getResponsePackageName())
                    .append(template.replace(Constant.VM, ""))
                    // TODO append 表名
                    .append(suffixConfig.getResponseSuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (TemplateEnum.CONTROLLER.getName().equals(template)) {
            // controller
            filePath.append(packageConfig.getControllerPackageName())
                    .append(template.replace(Constant.VM, ""))
                    // TODO append 表名
                    .append(suffixConfig.getControllerSuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (TemplateEnum.SERVICE.getName().equals(template)) {
            // service
            filePath.append(packageConfig.getServicePackageName())
                    .append(template.replace(Constant.VM, ""))
                    // TODO append 表名
                    .append(suffixConfig.getServiceSuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (TemplateEnum.SERVICE_IMPL.getName().equals(template)) {
            // serviceImpl
            filePath.append(packageConfig.getServiceImplPackageName())
                    .append(template.replace(Constant.VM, ""))
                    // TODO append 表名
                    .append(suffixConfig.getServiceImplSuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (TemplateEnum.MAPPER.getName().equals(template)) {
            // mapper
            filePath.append(packageConfig.getMapperPackageName())
                    .append(template.replace(Constant.VM, ""))
                    // TODO append 表名
                    .append(suffixConfig.getMapperSuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (TemplateEnum.MAPPER_XML.getName().equals(template)) {
            // mapper xml
            filePath.append(packageConfig.getMapperPackageName())
                    .append(template.replace(Constant.XML_VM, ""))
                    // TODO append 表名
                    .append(suffixConfig.getMapperSuffix())
                    .append(Constant.XML_SUFFIX);
        }

        return filePath.toString();
    }

    /**
     * 根据下划线名字获取首字母大写驼峰名
     * @param name
     * @return
     */
    public static String getClassName(String name) {
        return StringUtils.capitalizeFirstLetter(StrUtil.toCamelCase(name));
    }
}
