package com.github.cloud.aspectj;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author : glw
 * @date : 2020/11/29
 * @time : 13:43
 * @Description : Controller 日志切面基类
 */
@Slf4j
@Aspect
public class LogControllerAspect implements EnvironmentAware {

    private Environment environment;

    /**
     * 服务名
     */
    private static final String APP_NAME = "spring.application.name";

    /**
     * 拼接 Controller 日志字符串
     */
    private StringBuilder controllerLog = new StringBuilder();

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        String appName = environment.getProperty(APP_NAME);
        controllerLog.append("[service] ").append(appName).append(" : api = %s, class = %s, method = %s, args = %s");
    }

    @Pointcut(value = "execution(* com.github.cloud..controller..*Controller.*(..))")
    public void pointCut() {
    }

    /**
     * 根据 swagger 注解打印日志
     * @param joinPoint
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Annotation[] annotations = method.getAnnotations();
        if (null != annotations && annotations.length > 0) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof ApiOperation) {
                    String className = joinPoint.getTarget().getClass().getName();
                    StringBuilder argsInfo = new StringBuilder();
                    String apiValue = ((ApiOperation) annotation).value();
                    // 拼接对象参数
                    Arrays.asList(joinPoint.getArgs()).stream().forEach(item -> {
                        if (!(item instanceof BindingResult)) {
                            argsInfo.append(item.toString());
                        }
                    });
                    log.info(String.format(controllerLog.toString(), apiValue, className, signature.getName(), argsInfo.toString()));
                }
            }
        }
    }
}
