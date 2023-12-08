package com.runde.signinbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.runde.signinbackend.common.ErrorCode;
import com.runde.signinbackend.constant.CommonConstant;
import com.runde.signinbackend.exception.BusinessException;
import com.runde.signinbackend.mapper.ClassMapper;
import com.runde.signinbackend.model.dto.classInfo.ClassQueryListRequest;
import com.runde.signinbackend.model.entity.Class;
import com.runde.signinbackend.model.vo.ClassVO;
import com.runde.signinbackend.service.ClassService;
import com.runde.signinbackend.utils.SqlUtil;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

    @Override
    public ClassVO getClassVO(Class classInfo) {
        return classInfo == null? null : BeanUtil.copyProperties(classInfo, ClassVO.class);
    }

    @Override
    public QueryWrapper<Class> getQueryWrapper(ClassQueryListRequest classQueryListRequest) {

        if (classQueryListRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = classQueryListRequest.getName();
        String profile = classQueryListRequest.getProfile();
        String code = classQueryListRequest.getCode();
        Integer createUser = classQueryListRequest.getCreateUser();
        Integer isDelete = classQueryListRequest.getIsDelete();
        String sortField = classQueryListRequest.getSortField();
        String sortOrder = classQueryListRequest.getSortOrder();
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(name != null,"name",name);
        queryWrapper.like(profile != null,"profile",profile);
        queryWrapper.like(code != null,"code",code);
        queryWrapper.eq(createUser != null,"create_user",createUser);
        queryWrapper.eq(isDelete != null, "is_delete", isDelete);
        queryWrapper.orderBy(SqlUtil.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}
