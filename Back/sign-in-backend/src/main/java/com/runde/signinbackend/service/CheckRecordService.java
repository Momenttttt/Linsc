package com.runde.signinbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.runde.signinbackend.model.dto.CheckRecord.CheckRecordAddRequest;
import com.runde.signinbackend.model.dto.CheckRecord.CheckRecordQueryListRequest;
import com.runde.signinbackend.model.dto.CheckRecord.CheckRecordUpdateRequest;
import com.runde.signinbackend.model.entity.CheckInfo;
import com.runde.signinbackend.model.entity.CheckRecord;

public interface CheckRecordService extends IService<CheckRecord> {
    /**
     * 添加签到记录
     * @param checkRecordAddRequest
     * @return
     */
    Long addCheckRecord(CheckRecordAddRequest checkRecordAddRequest);

    /**
     * 删除签到记录
     * @param id
     */
    void changeIsDelete(Long id);

    /**
     * 条件查询器
     * @param checkRecordQueryListRequest
     * @return
     */

    QueryWrapper<CheckRecord> getQueryWrapper(CheckRecordQueryListRequest checkRecordQueryListRequest);

    /**
     * 更新签到记录
     * @param checkRecordUpdateRequest
     */
    void updateCheckIn(CheckRecordUpdateRequest checkRecordUpdateRequest);
}
