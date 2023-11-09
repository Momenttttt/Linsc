package com.runde.signinbackend.manager;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.runde.signinbackend.config.CosClientConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.manager
 * @Author 最紧要开心
 * @CreateTime 2023/11/9 8:58
 * @Description 对象存储管理器
 * @Version 1.0
 */
@Component
public class CosManager {

    @Resource
    private CosClientConfig cosClientConfig;

    @Resource
    private COSClient cosClient;

    /**
     * 上传本地文件
     *
     * @param key 唯一键
     * @param localFilePath 本地文件路径
     */
    public PutObjectResult putObject(String key, String localFilePath) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key, new File(localFilePath));
        return cosClient.putObject(putObjectRequest);
    }

    /**
     * 上传文件
     *
     * @param key 唯一键
     * @param file 文件
     */
    public PutObjectResult putObject(String key, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key, file);
        return cosClient.putObject(putObjectRequest);
    }
}