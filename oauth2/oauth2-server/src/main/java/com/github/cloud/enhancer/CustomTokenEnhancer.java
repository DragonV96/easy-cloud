package com.github.cloud.enhancer;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : glw
 * @datetime : 2021/1/15 0:33
 * @Description : 自定义增强器扩展 token 内容
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        if (null != userAuthentication) {
            Map<String, Object> additionInfo = new HashMap<>();
            additionInfo.put("userDetail", userAuthentication.getPrincipal());
            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionInfo);
        }
        return oAuth2AccessToken;
    }
}
