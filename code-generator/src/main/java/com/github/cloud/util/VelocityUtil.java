package com.github.cloud.util;

import com.github.cloud.config.PackageConfig;
import com.github.cloud.config.ProjectConfig;
import com.github.cloud.config.SuffixConfig;
import com.github.cloud.config.SwitchConfig;
import org.apache.velocity.VelocityContext;

/**
 * @author : glw
 * @date : 2020/12/11
 * @time : 1:01
 * @Description : velocity 模板引擎工具类
 */
public class VelocityUtil {

    /**
     * 项目包结构及名称配置
     * @param packageConfig
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

}
