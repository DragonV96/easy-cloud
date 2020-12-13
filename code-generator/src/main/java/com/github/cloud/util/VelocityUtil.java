package com.github.cloud.util;

import com.github.cloud.config.PackageConfig;
import org.apache.velocity.VelocityContext;

/**
 * @author : glw
 * @date : 2020/12/11
 * @time : 1:01
 * @Description : velocity 模板引擎工具类
 */
public class VelocityUtil {

    /**
     * 设置包路径模板
     * @param config
     * @return
     */
    public static VelocityContext setTemplate(PackageConfig config) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("packageName", config.getPackageName());
        velocityContext.put("moduleName", config.getModuleName());
        velocityContext.put("controllerPackageName", config.getControllerPackageName());
        velocityContext.put("servicePackageName", config.getServicePackageName());
        velocityContext.put("serviceImplPackageName", config.getServiceImplPackageName());
        velocityContext.put("dtoPackageName", config.getDtoPackageName());
        velocityContext.put("requestPackageName", config.getRequestPackageName());
        velocityContext.put("responsePackageName", config.getResponsePackageName());
        velocityContext.put("requestSuffix", config.getRequestSuffix());
        velocityContext.put("responseSuffix", config.getResponseSuffix());
        return velocityContext;
    }

}
