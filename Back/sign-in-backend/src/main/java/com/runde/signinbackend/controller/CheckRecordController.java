package com.runde.signinbackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.runde.signinbackend.common.BaseResponse;
import com.runde.signinbackend.common.ErrorCode;
import com.runde.signinbackend.exception.BusinessException;
import com.runde.signinbackend.model.dto.CheckRecord.CheckRecordAddRequest;
import com.runde.signinbackend.model.dto.CheckRecord.CheckRecordQueryListRequest;
import com.runde.signinbackend.model.dto.CheckRecord.CheckRecordQueryOneRequest;
import com.runde.signinbackend.model.dto.CheckRecord.CheckRecordUpdateRequest;
import com.runde.signinbackend.model.entity.CheckRecord;
import com.runde.signinbackend.service.CheckRecordService;
import com.runde.signinbackend.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 签到记录接口
 * @author felix
 */
@RestController
@RequestMapping("/checkRecord")
@Slf4j
@Api(tags = "4.签到记录模块")
public class CheckRecordController {
    @Autowired
    private CheckRecordService checkRecordService;
    /**
     * 添加签到记录
     * @param checkRecordAddRequest
     * @return
     */
    @ApiOperation("添加签到记录")
    @ApiOperationSupport(order = 1)
    @PostMapping("/addCheckRecord")
    public BaseResponse<Long> addCheckRecord(@RequestBody CheckRecordAddRequest checkRecordAddRequest) {
        if (checkRecordAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        Long id = checkRecordService.addCheckRecord(checkRecordAddRequest);
        return ResultUtil.success(id);
    }

    @ApiOperation("删除签到记录")
    @ApiOperationSupport(order = 2)
    @PostMapping("/inValCheckRecord")
    public BaseResponse<Boolean> inValCheckRecord(@RequestParam("id") Long id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        checkRecordService.changeIsDelete(id);
        return ResultUtil.success(true);
    }

    /**
     * 查询单个签到记录
     * @param checkRecordQueryOneRequest
     * @return
     */
    @ApiOperation("查询单个签到记录")
    @ApiOperationSupport(order = 3)
    @PostMapping("/getOneCheckRecord")
    public BaseResponse<CheckRecord> getOneCheckRecord(@RequestBody CheckRecordQueryOneRequest checkRecordQueryOneRequest) {
        if (checkRecordQueryOneRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        CheckRecord byId = checkRecordService.getById(checkRecordQueryOneRequest.getId());
        return ResultUtil.success(byId);
    }

    /**
     * 获取多个签到信息请求
     * @param checkRecordQueryListRequest
     * @return
     */
    @ApiOperation("分页获取多个签到记录")
    @ApiOperationSupport(order = 4)
    @PostMapping("/list/checkRecord")
    public BaseResponse<Page<CheckRecord>> getListCheckRecord(@RequestBody CheckRecordQueryListRequest checkRecordQueryListRequest) {
        if (checkRecordQueryListRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取分页信息
        Long current = checkRecordQueryListRequest.getCurrent();
        Long pageSize = checkRecordQueryListRequest.getPageSize();
        Page<CheckRecord> checkRecord = checkRecordService.page(new Page<>(current, pageSize),
                checkRecordService.getQueryWrapper(checkRecordQueryListRequest));
        return ResultUtil.success(checkRecord);
    }
    /**
     * 更新签到信息
     * @param checkRecordUpdateRequest
     * @return
     */
    @ApiOperation("更新签到记录")
    @ApiOperationSupport(order = 5)
    @PostMapping("/updateCheckInfo")
    public BaseResponse<Boolean> updateCheckInfo(@RequestBody CheckRecordUpdateRequest checkRecordUpdateRequest) {
        if (checkRecordUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        checkRecordService.updateCheckIn(checkRecordUpdateRequest);
        return ResultUtil.success(true);
    }
}
