package com.runde.signinbackend.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.region.Region;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.config
 * @Author 最紧要开心
 * @CreateTime 2023/11/9 8:50
 * @Description 对象存储配置
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "cos.client")
@Getter
@Setter
public class CosClientConfig {

    /**
     * accessKey
     */
    private String accessKey;

    /**
     * secretKey
     */
    private String secretKey;

    /**
     * 区域
     */
    private String region;

    /**
     * 桶
     */
    private String bucket;

    @Bean
    public COSClient cosClient() {
        // 初始化用户凭证
        BasicCOSCredentials basicCOSCredentials = new BasicCOSCredentials(accessKey, secretKey);
        // 初始化客户端配置（bucket地域，参考 https://www.qcloud.com/document/product/436/6224）
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 获取客户端
        return new COSClient(basicCOSCredentials, clientConfig);
    }
}
