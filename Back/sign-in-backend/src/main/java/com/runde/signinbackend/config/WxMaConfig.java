package com.runde.signinbackend.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.config
 * @Author 最紧要开心
 * @CreateTime 2023/11/7 11:51
 * @Description 微信开放平台配置
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "wx.ma")
@Getter
@Setter
public class WxMaConfig {

    private String appId;

    private String appSecret;

    @Bean
    public WxMaService wxMaService() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(appId);
        config.setSecret(appSecret);
        WxMaServiceImpl wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(config);
        return wxMaService;
    }
}
