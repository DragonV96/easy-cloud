package com.github.cloud.interceptor;

import com.github.cloud.strategy.FeignHystrixConcurrencyStrategy;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author : glw
 * @datetime : 2021/5/18 10:56
 * @description : openfeign 调用，放入请求头
 */
@Slf4j
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    private final static String AUTHORIZATION = "authorization";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            return;
        }

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (null != headerNames) {
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                if (!StringUtils.isEmpty(key) && AUTHORIZATION.equals(key)) {
                    String token = request.getHeader(key);
                    log.info(" >>> Put token info into the http request header, token = {}", token);
                    requestTemplate.header(key, token);
                    return;
                }
            }
        }
    }

    @Bean
    public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy() {
        return new FeignHystrixConcurrencyStrategy();
    }
}
