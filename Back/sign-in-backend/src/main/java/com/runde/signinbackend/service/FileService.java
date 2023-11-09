package com.runde.signinbackend.service;

import com.runde.signinbackend.model.enums.FileUploadBizEnum;
import org.springframework.web.multipart.MultipartFile;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.service
 * @Author 最紧要开心
 * @CreateTime 2023/11/9 0:37
 * @Description 文件服务
 * @Version 1.0
 */
public interface FileService {

    /**
     * 文件校验
     *
     * @param multipartFile
     * @param fileUploadBizEnum
     */
    void validFile(MultipartFile multipartFile, FileUploadBizEnum fileUploadBizEnum);
}
