package com.runde.signinbackend.model.dto.file;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "业务（当前支持 user_avatar）", required = true)
    private String biz;

    private static final long serialVersionUID = 1L;
}
