package com.runde.signinbackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.runde.signinbackend.common.BaseResponse;
import com.runde.signinbackend.common.ErrorCode;
import com.runde.signinbackend.exception.BusinessException;
import com.runde.signinbackend.model.dto.checkInfo.CheckInfoAddRequest;
import com.runde.signinbackend.model.dto.checkInfo.CheckInfoQueryListRequest;
import com.runde.signinbackend.model.dto.checkInfo.CheckInfoUpdateRequest;
import com.runde.signinbackend.model.entity.CheckInfo;
import com.runde.signinbackend.service.CheckInfoService;
import com.runde.signinbackend.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 签到信息接口
 * @author felix
 */
@RestController
@RequestMapping("/checkinInfo")
@Slf4j
@Api(tags = "3.签到信息模块")
public class CheckInfoController {
    @Autowired
    private CheckInfoService checkInfoService;

    private final Integer INVALIDATION = 1;
    private final Integer VALID = 0;
    /**
     * 添加签到
     * @param checkInfoAddRequest
     * @return
     */
    @ApiOperation("添加签到")
    @ApiOperationSupport(order = 1)
    @PostMapping("/addCheckInfo")
    public BaseResponse<Long> addCheckInfo(@RequestBody CheckInfoAddRequest checkInfoAddRequest) {
        if (checkInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        Long id = checkInfoService.addCheckIn(checkInfoAddRequest);
        return ResultUtil.success(id);
    }

    /**
     * 使签到失效
     * @param id
     * @return
     */
    @ApiOperation("使签到失效")
    @ApiOperationSupport(order = 2)
    @PostMapping("/inValCheckIn")
    public BaseResponse<Boolean> inValCheckIn(@RequestParam("id") Long id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        checkInfoService.changeIsDelete(id,INVALIDATION);
        return ResultUtil.success(true);
    }

    /**
     * 获取单个签到信息请求
     * @param id
     * @return
     */
    @ApiOperation("获取单个签到信息")
    @ApiOperationSupport(order = 3)
    @GetMapping("/getOneCheckInfo")
    public BaseResponse<CheckInfo> getOneCheckInfo(@RequestParam("id") String id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        CheckInfo checkInfoById = checkInfoService.getById(id);
        return ResultUtil.success(checkInfoById);
    }

    /**
     * 获取多个签到信息请求
     * @param checkInfoQueryListRequest
     * @return
     */
    @ApiOperation("分页获取多个签到信息")
    @ApiOperationSupport(order = 4)
    @PostMapping("/list/checkInfo")
    public BaseResponse<Page<CheckInfo>> getListCheckInfo(@RequestBody CheckInfoQueryListRequest checkInfoQueryListRequest) {
        if (checkInfoQueryListRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        System.out.println(checkInfoQueryListRequest);
        // 获取分页信息
        Long current = checkInfoQueryListRequest.getCurrent();
        Long pageSize = checkInfoQueryListRequest.getPageSize();
        Page<CheckInfo> checkInfo = checkInfoService.page(new Page<>(current, pageSize),
                checkInfoService.getQueryWrapper(checkInfoQueryListRequest));
        return ResultUtil.success(checkInfo);
    }

    /**
     * 更新签到信息
     * @param checkInfoUpdateRequest
     * @return
     */
    @ApiOperation("更新签到信息")
    @ApiOperationSupport(order = 5)
    @PostMapping("/updateCheckInfo")
    public BaseResponse<Boolean> updateCheckInfo(@RequestBody CheckInfoUpdateRequest checkInfoUpdateRequest) {
        if (checkInfoUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        checkInfoService.updateCheckIn(checkInfoUpdateRequest);
        return ResultUtil.success(true);
    }

}
