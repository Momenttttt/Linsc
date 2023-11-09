package com.runde.signinbackend.model.dto.file;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.model.dto.file.UploadFileRequest
 * @Author 最紧要开心
 * @CreateTime 2023/11/9 0:22
 * @Description 文件上传请求
 * @Version 1.0
 */
@Getter
@Setter
public class UploadFileRequest implements Serializable {

    /**
     * 业务
     */
    private String biz;

    private static final long serialVersionUID = 1L;
}
