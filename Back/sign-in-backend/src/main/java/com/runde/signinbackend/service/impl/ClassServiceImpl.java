package com.runde.signinbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.runde.signinbackend.mapper.ClassMapper;
import com.runde.signinbackend.model.entity.Class;
import com.runde.signinbackend.service.ClassService;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

}
