package com.runde.signinbackend.service.impl;

import cn.hutool.core.io.FileUtil;
import com.runde.signinbackend.common.ErrorCode;
import com.runde.signinbackend.constant.FileConstant;
import com.runde.signinbackend.exception.BusinessException;
import com.runde.signinbackend.model.enums.FileUploadBizEnum;
import com.runde.signinbackend.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.service.impl
 * @Author 最紧要开心
 * @CreateTime 2023/11/9 8:31
 * @Description 文件服务实现
 * @Version 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public void validFile(MultipartFile multipartFile, FileUploadBizEnum fileUploadBizEnum) {
        // 文件大小
        long size = multipartFile.getSize();
        // 文件类型
        String suffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        // 用户上传头像
        if(FileUploadBizEnum.USER_AVATAR.equals(fileUploadBizEnum)) {
            if (size > FileConstant.ONE_M) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件大小不能超过 10M");
            }
            if (!Arrays.asList("jepg", "jpg", "webp", "svg", "png").contains(suffix)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "只支持 jepg, jpg, webp, svg, png 格式");
            }
        }
    }
}
