package com.runde.signinbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.runde.signinbackend.model.dto.checkInfo.CheckInfoAddRequest;
import com.runde.signinbackend.model.dto.checkInfo.CheckInfoQueryListRequest;
import com.runde.signinbackend.model.dto.checkInfo.CheckInfoUpdateRequest;
import com.runde.signinbackend.model.dto.user.UserQueryListRequest;
import com.runde.signinbackend.model.entity.CheckInfo;
import com.runde.signinbackend.model.entity.User;


public interface CheckInfoService extends IService<CheckInfo> {
    /**
     * 添加签到
     *
     * @param checkInfoAddRequest
     */
    Long addCheckIn(CheckInfoAddRequest checkInfoAddRequest);


    void changeIsDelete(Long id, int invalidation);

    /**
     * 获取查询条件包装器
     *
     * @param checkInfoQueryListRequest
     */
    QueryWrapper<CheckInfo> getQueryWrapper(CheckInfoQueryListRequest checkInfoQueryListRequest);

    /**
     * 更新签到信息
     * @param checkInfoUpdateRequest
     */
    void updateCheckIn(CheckInfoUpdateRequest checkInfoUpdateRequest);
}
