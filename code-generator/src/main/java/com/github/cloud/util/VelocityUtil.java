package com.github.cloud.util;

import cn.hutool.core.date.DateUtil;
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

    /**
     * 初始化 velocity 引擎
     */
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
     * @param projectConfig
     * @param packageConfig
     * @param switchConfig
     * @param suffixConfig
     * @return
     */
    public static VelocityContext buildContext(ProjectConfig projectConfig, PackageConfig packageConfig, SwitchConfig switchConfig, SuffixConfig suffixConfig) {
        VelocityContext velocityContext = new VelocityContext();

        // 项目开关配置
        velocityContext.put("enableEmail", switchConfig.getEnableEmail());
        velocityContext.put("enableSwagger", switchConfig.getEnableSwagger());
        velocityContext.put("enableShiro", switchConfig.getEnableShiro());

        // 项目信息配置
        velocityContext.put("author", projectConfig.getAuthor());
        velocityContext.put("email", projectConfig.getEmail());
        velocityContext.put("datetime", DateUtil.now());

        // 项目类后缀配置
        velocityContext.put("entitySuffix", suffixConfig.getEntitySuffix());
        velocityContext.put("requestSuffix", suffixConfig.getRequestSuffix());
        velocityContext.put("responseSuffix", suffixConfig.getResponseSuffix());
        velocityContext.put("controllerSuffix", suffixConfig.getControllerSuffix());
        velocityContext.put("serviceSuffix", suffixConfig.getServiceSuffix());
        velocityContext.put("serviceImplSuffix", suffixConfig.getServiceImplSuffix());
        velocityContext.put("mapperSuffix", suffixConfig.getMapperSuffix());

        // 项目包结构及名称配置
        velocityContext.put("rootPackageName", packageConfig.getRootPackageName());
        velocityContext.put("modulePackageName", packageConfig.getModulePackageName());
        velocityContext.put("controllerPackageName", packageConfig.getControllerPackageName());
        velocityContext.put("servicePackageName", packageConfig.getServicePackageName());
        velocityContext.put("serviceImplPackageName", packageConfig.getServiceImplPackageName());
        velocityContext.put("mapperPackageName", packageConfig.getMapperPackageName());
        velocityContext.put("entityPackageName", packageConfig.getEntityPackageName());
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
     * @param allowTable
     * @param template
     * @return
     */
    public static String getFileName(ProjectConfig projectConfig, PackageConfig packageConfig, SuffixConfig suffixConfig, TableConfig.AllowTable allowTable, String template) {
        StringBuilder filePath = new StringBuilder();
        String className = getClassName(allowTable.getTableName());

        // 公共包路径
        filePath.append(projectConfig.getPath())
                .append(Constant.SPLIT)
                .append(projectConfig.getProjectName())
                .append(Constant.SPLIT);

        if (template.endsWith(TemplateEnum.MAPPER_XML.getName())) {
            // xml 文件前缀
            fillResourcesPath(filePath);
        } else {
            // java 文件前缀
            fillJavaPath(filePath, packageConfig);
        }

        if (template.endsWith(TemplateEnum.ADD.getName()) ||
                template.endsWith(TemplateEnum.UPDATE.getName()) ||
                template.endsWith(TemplateEnum.PAGE.getName())) {
            // 请求对象
            filePath.append(packageConfig.getDtoPackageName())
                    .append(Constant.SPLIT)
                    .append(packageConfig.getRequestPackageName())
                    .append(Constant.SPLIT)
                    .append(template.replace(Constant.TEMPLATE_PATH, "").replace(Constant.TEMPLATE_PATH, "").replace(Constant.VM, ""))
                    .append(className)
                    .append(suffixConfig.getRequestSuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (template.endsWith(TemplateEnum.RESPONSE.getName())) {
            // 响应对象
            filePath.append(packageConfig.getDtoPackageName())
                    .append(Constant.SPLIT)
                    .append(packageConfig.getResponsePackageName())
                    .append(Constant.SPLIT)
                    .append(className)
                    .append(suffixConfig.getResponseSuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (template.endsWith(TemplateEnum.ENTITY.getName())) {
            // 数据库实体类
            filePath.append(packageConfig.getEntityPackageName())
                    .append(Constant.SPLIT)
                    .append(className)
                    .append(suffixConfig.getEntitySuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (template.endsWith(TemplateEnum.CONTROLLER.getName())) {
            // controller
            filePath.append(packageConfig.getControllerPackageName())
                    .append(Constant.SPLIT)
                    .append(className)
                    .append(suffixConfig.getControllerSuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (template.endsWith(TemplateEnum.SERVICE.getName())) {
            // service
            filePath.append(packageConfig.getServicePackageName())
                    .append(Constant.SPLIT)
                    .append(className)
                    .append(suffixConfig.getServiceSuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (template.endsWith(TemplateEnum.SERVICE_IMPL.getName())) {
            // serviceImpl
            filePath.append(packageConfig.getServicePackageName())
                    .append(Constant.SPLIT)
                    .append(packageConfig.getServiceImplPackageName())
                    .append(Constant.SPLIT)
                    .append(className)
                    .append(suffixConfig.getServiceImplSuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (template.endsWith(TemplateEnum.MAPPER.getName())) {
            // mapper
            filePath.append(packageConfig.getMapperPackageName())
                    .append(Constant.SPLIT)
                    .append(className)
                    .append(suffixConfig.getMapperSuffix())
                    .append(Constant.JAVA_SUFFIX);
        } else if (template.endsWith(TemplateEnum.MAPPER_XML.getName())) {
            // mapper xml
            filePath.append(template.replace(Constant.TEMPLATE_PATH, "").replace(Constant.XML_VM, ""))
                    .append(className)
                    .append(suffixConfig.getMapperSuffix())
                    .append(Constant.XML_SUFFIX);
        }

        return filePath.toString();
    }

    /**
     * 填充 java 文件路径
     * @param filePath
     * @param packageConfig
     */
    private static void fillJavaPath(StringBuilder filePath, PackageConfig packageConfig) {
        // java 公共包路径
        filePath.append(Constant.PROJECT_PATH)
                .append(Constant.SPLIT)
                .append(packageConfig.getRootPackageName().replace(Constant.DOT, Constant.SPLIT))
                .append(Constant.SPLIT)
                .append(packageConfig.getModulePackageName())
                .append(Constant.SPLIT);
    }

    /**
     * 填充 resources 文件路径
     * @param filePath
     */
    private static void fillResourcesPath(StringBuilder filePath) {
        // resources 公共包路径
        filePath.append(Constant.MYBATIS_PATH)
                .append(Constant.SPLIT);
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
