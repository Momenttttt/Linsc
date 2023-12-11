package com.runde.signinbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.runde.signinbackend.common.ErrorCode;
import com.runde.signinbackend.constant.CommonConstant;
import com.runde.signinbackend.exception.BusinessException;
import com.runde.signinbackend.mapper.CheckRecordMapper;
import com.runde.signinbackend.model.dto.CheckRecord.CheckRecordAddRequest;
import com.runde.signinbackend.model.dto.CheckRecord.CheckRecordQueryListRequest;
import com.runde.signinbackend.model.dto.CheckRecord.CheckRecordUpdateRequest;
import com.runde.signinbackend.model.entity.CheckInfo;
import com.runde.signinbackend.model.entity.CheckRecord;
import com.runde.signinbackend.service.CheckRecordService;
import com.runde.signinbackend.utils.SqlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CheckRecordServiceImpl extends ServiceImpl<CheckRecordMapper, CheckRecord> implements CheckRecordService {
    @Override
    public Long addCheckRecord(CheckRecordAddRequest checkRecordAddRequest) {
        Long signinUser = checkRecordAddRequest.getSigninUser();
        Date createTime = checkRecordAddRequest.getCreateTime();
        String location = checkRecordAddRequest.getLocation();
        Integer status = checkRecordAddRequest.getStatus();
        if(createTime==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "创建时间不能为空");
        }
        if(status==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "状态不能为空");
        }
        if(signinUser==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "签到者不能为空");
        }
        if (StringUtils.isBlank(location)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "位置信息不能为空");
        }
        CheckRecord checkRecord = new CheckRecord();
        BeanUtils.copyProperties(checkRecordAddRequest,checkRecord);
        this.save(checkRecord);
        return checkRecord.getId();
    }

    @Override
    public void changeIsDelete(Long id) {
        this.removeById(id);
    }

    @Override
    public QueryWrapper<CheckRecord> getQueryWrapper(CheckRecordQueryListRequest checkRecordQueryListRequest) {
        if (checkRecordQueryListRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String location = checkRecordQueryListRequest.getLocation();
        Long signinUser = checkRecordQueryListRequest.getSigninUser();
        Integer status = checkRecordQueryListRequest.getStatus();
        Integer isDelete = checkRecordQueryListRequest.getIsDelete();

        String sortField = checkRecordQueryListRequest.getSortField();
        String sortOrder = checkRecordQueryListRequest.getSortOrder();
        QueryWrapper<CheckRecord> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(signinUser != null, "signin_user", signinUser);
        queryWrapper.eq(status != null, "status", status);
        queryWrapper.eq(StringUtils.isNotBlank(location), "location", location);
        queryWrapper.eq(isDelete != null, "is_delete", isDelete);

        queryWrapper.orderBy(SqlUtil.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }

    @Override
    public void updateCheckIn(CheckRecordUpdateRequest checkRecordUpdateRequest) {
        String location = checkRecordUpdateRequest.getLocation();
        Integer status = checkRecordUpdateRequest.getStatus();
        Long id = checkRecordUpdateRequest.getId();
        Long signinUser = checkRecordUpdateRequest.getSigninUser();

        UpdateWrapper<CheckRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(id!=null,"id",id)
                .set(StringUtils.isNotBlank(location),"location",location)
                .set(signinUser!=null,"signin_user",signinUser)
                .set(status!=null,"status",status);
        this.update(null, updateWrapper);
    }

}
