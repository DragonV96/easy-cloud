package com.github.cloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author : glw
 * @datetime : 2021/1/20 1:10
 * @Description : Web 安全配置
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 只允许匿名访问 / 和 /login 路径，其他路径需要认证授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "login**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
