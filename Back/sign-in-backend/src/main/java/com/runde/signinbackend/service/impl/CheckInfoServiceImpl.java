package com.runde.signinbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.runde.signinbackend.common.ErrorCode;
import com.runde.signinbackend.constant.CommonConstant;
import com.runde.signinbackend.exception.BusinessException;
import com.runde.signinbackend.mapper.CheckInfoMapper;
import com.runde.signinbackend.model.dto.checkInfo.CheckInfoAddRequest;
import com.runde.signinbackend.model.dto.checkInfo.CheckInfoQueryListRequest;
import com.runde.signinbackend.model.dto.checkInfo.CheckInfoUpdateRequest;
import com.runde.signinbackend.model.dto.user.UserQueryListRequest;
import com.runde.signinbackend.model.entity.CheckInfo;
import com.runde.signinbackend.model.entity.User;
import com.runde.signinbackend.service.CheckInfoService;
import com.runde.signinbackend.utils.SqlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class CheckInfoServiceImpl extends ServiceImpl<CheckInfoMapper, CheckInfo> implements CheckInfoService {
    private final Integer NOT_DELETE = 0;
    @Override
    public Long addCheckIn(CheckInfoAddRequest checkInfoAddRequest) {
        Long classId = checkInfoAddRequest.getClassId();
        Date createTime = checkInfoAddRequest.getCreateTime();
        Integer expireTime = checkInfoAddRequest.getExpireTime();
        Long createUser = checkInfoAddRequest.getCreateUser();
        String location = checkInfoAddRequest.getLocation();
        if(createTime==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "创建时间不能为空");
        }
        if(expireTime==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "过期时间不能为空");
        }
        if(createUser==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "创建者不能为空");
        }
        if(classId==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "班级id不能为空");
        }
        if (StringUtils.isBlank(location)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "位置信息不能为空");
        }
        CheckInfo checkInfo = new CheckInfo();
        BeanUtils.copyProperties(checkInfoAddRequest,checkInfo);
        checkInfo.setIsDelete(NOT_DELETE);
        this.save(checkInfo);
        // todo 数据库插入成功后将id插入redis，并设置TTl ?
        return checkInfo.getId();
    }

    @Override
    public void changeIsDelete(Long id, int value) {
        UpdateWrapper<CheckInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id).set("is_delete",value);
        CheckInfo checkInfo = new CheckInfo();
        checkInfo.setIsDelete(value);
        this.update(null, updateWrapper);
    }

    @Override
    public QueryWrapper<CheckInfo> getQueryWrapper(CheckInfoQueryListRequest checkInfoQueryListRequest) {
        if (checkInfoQueryListRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long classId = checkInfoQueryListRequest.getClassId();
        String profile = checkInfoQueryListRequest.getProfile();
        String location = checkInfoQueryListRequest.getLocation();
        Long createUser = checkInfoQueryListRequest.getCreateUser();
        Integer isDelete = checkInfoQueryListRequest.getIsDelete();

        String sortField = checkInfoQueryListRequest.getSortField();
        String sortOrder = checkInfoQueryListRequest.getSortOrder();
        QueryWrapper<CheckInfo> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(classId != null, "class_id", classId);
        queryWrapper.like(StringUtils.isNotBlank(profile), "profile", profile);
        queryWrapper.eq(createUser != null, "create_user", createUser);
        queryWrapper.eq(StringUtils.isNotBlank(location), "location", location);
        queryWrapper.eq(isDelete != null, "is_delete", isDelete);
        queryWrapper.orderBy(SqlUtil.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }

    @Override
    public void updateCheckIn(CheckInfoUpdateRequest checkInfoUpdateRequest) {
        Long classId = checkInfoUpdateRequest.getClassId();
        String location = checkInfoUpdateRequest.getLocation();
        Long id = checkInfoUpdateRequest.getId();
        Integer expireTime = checkInfoUpdateRequest.getExpireTime();
        String profile = checkInfoUpdateRequest.getProfile();

        UpdateWrapper<CheckInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id)
                .set(StringUtils.isNotBlank(location),"location",location)
                .set(classId!=null,"class_id",classId)
                .set(expireTime!=null,"expire_time",expireTime)
                .set(StringUtils.isNotBlank(profile),"profile",profile);
        this.update(null, updateWrapper);
    }

}
