package com.runde.signinbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.runde.signinbackend.model.dto.classInfo.ClassQueryListRequest;
import com.runde.signinbackend.model.entity.Class;
import com.runde.signinbackend.model.vo.ClassVO;

import java.util.List;

public interface ClassService extends IService<Class> {

    ClassVO getClassVO(Class classInfo);

    QueryWrapper<Class> getQueryWrapper(ClassQueryListRequest classQueryListRequest);

    List<ClassVO> getClassVOList(List<Class> classes);
}
