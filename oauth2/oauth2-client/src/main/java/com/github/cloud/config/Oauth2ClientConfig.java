package com.github.cloud.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

/**
 * @author : glw
 * @datetime : 2021/1/20 1:13
 * @Description : 配置 Oauth2 的 http 调用客户端，授权认证后则无需在请求头里加入 Header 进行校验
 */
@Configuration
@EnableOAuth2Sso
public class Oauth2ClientConfig {

    /**
     * 定义 OAuth2RestTemplate
     * @param factory
     * @return
     */
    @LoadBalanced
    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(UserInfoRestTemplateFactory factory) {
        return factory.getUserInfoRestTemplate();
    }
}
