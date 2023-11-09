package com.runde.signinbackend.controller;

import com.runde.signinbackend.common.BaseResponse;
import com.runde.signinbackend.common.ErrorCode;
import com.runde.signinbackend.constant.FileConstant;
import com.runde.signinbackend.exception.BusinessException;
import com.runde.signinbackend.manager.CosManager;
import com.runde.signinbackend.model.dto.file.UploadFileRequest;
import com.runde.signinbackend.model.entity.User;
import com.runde.signinbackend.model.enums.FileUploadBizEnum;
import com.runde.signinbackend.service.FileService;
import com.runde.signinbackend.service.UserService;
import com.runde.signinbackend.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.controller
 * @Author 最紧要开心
 * @CreateTime 2023/11/9 0:16
 * @Description 文件接口
 * @Version 1.0
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private UserService userService;

    @Resource
    private FileService fileService;

    @Resource
    private CosManager cosManager;

    public BaseResponse<String> uploadFile(@RequestPart("file")MultipartFile multipartFile,
                                           @RequestBody UploadFileRequest uploadFileRequest, HttpServletRequest request) {
        if (uploadFileRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        String biz = uploadFileRequest.getBiz();
        FileUploadBizEnum fileUploadBizEnum = FileUploadBizEnum.getEnumByValue(biz);
        if (fileUploadBizEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 校验文件合法性
        fileService.validFile(multipartFile, fileUploadBizEnum);
        // 获取登录用户
        User user = userService.getLoginUser(request);
        // 上传文件
        String uuid = RandomStringUtils.randomAlphanumeric(8);
        String fileName = uuid + "-" + multipartFile.getOriginalFilename();
        String filePath = String.format("/%s/%s/%s", fileUploadBizEnum.getValue(), user.getId(), fileName);
        File file = null;
        try {
            file = File.createTempFile(filePath, null);
            multipartFile.transferTo(file);
            cosManager.putObject(filePath, file);
            return ResultUtil.success(FileConstant.COS_HOST + filePath);
        } catch (Exception e) {
            log.error("File upload failed, filePath = " + filePath, e);
            throw new BusinessException(ErrorCode.OPERATION_FAILED_ERROR, "文件上传失败");
        } finally {
            if (file != null && !file.delete()) {
                log.error("Temporary file delete failed, filePath = {}", filePath);
            }
        }
    }
}