package com.runde.signinbackend.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.runde.signinbackend.annotation.AuthCheck;
import com.runde.signinbackend.common.BaseResponse;
import com.runde.signinbackend.common.ErrorCode;
import com.runde.signinbackend.constant.UserConstant;
import com.runde.signinbackend.exception.BusinessException;
import com.runde.signinbackend.model.dto.classInfo.ClassAddRequest;
import com.runde.signinbackend.model.dto.classInfo.ClassQueryListRequest;
import com.runde.signinbackend.model.dto.classInfo.ClassUpdateRequest;
import com.runde.signinbackend.model.entity.Class;
import com.runde.signinbackend.model.vo.ClassVO;
import com.runde.signinbackend.service.ClassService;
import com.runde.signinbackend.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/class")
@Api(tags = "3.班级模块")
public class ClassController {

    @Resource
    private ClassService classService;

    /**
     * 添加用户
     *
     * @param
     */
    @ApiOperation("添加班级")
    @ApiOperationSupport(order = 1)
    @PostMapping("/add")
    public BaseResponse<Long> addClass(@RequestBody ClassAddRequest classAddRequest) {
        if (classAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Class classInfo = BeanUtil.copyProperties(classAddRequest, Class.class);
        Random random = new Random();
        //随机生成一个五位数字班级码
        classInfo.setCode(Integer.toString(random.nextInt(90000) + 10000));
        // 校验用户添加参数
        boolean result = classService.save(classInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_FAILED_ERROR, "班级添加失败");
        }
        return ResultUtil.success(classInfo.getId());
    }

    /**
     * 修改班级
     *
     * @param classUpdateRequest
     */
    @ApiOperation("修改班级")
    @ApiOperationSupport(order = 2)
    @PostMapping("/update")
    public BaseResponse<Boolean> updateClass(@RequestBody ClassUpdateRequest classUpdateRequest) {
        if (classUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Class classInfo = BeanUtil.copyProperties(classUpdateRequest, Class.class);
        boolean result = classService.updateById(classInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_FAILED_ERROR, "班级修改失败");
        }
        return ResultUtil.success(true);
    }

    /**
     * 删除班级
     *
     * @param id
     */
    @ApiOperation("删除班级")
    @ApiOperationSupport(order = 3)
    @ApiImplicitParam(name = "id", value = "班级id", dataType = "Long", dataTypeClass = Long.class)
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteClass(@RequestParam("id") Long id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id不能为空");
        }
        boolean result = classService.removeById(id);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_FAILED_ERROR, "班级删除失败");
        }
        return ResultUtil.success(true);
    }

    /**
     * 获取班级视图（脱敏）
     *
     * @param id
     */
    @ApiOperation("根据id获取班级脱敏信息")
    @ApiOperationSupport(order = 4)
    @ApiImplicitParam(name = "id", value = "班级id", dataType = "Long", dataTypeClass = Long.class)
    @GetMapping("/get/vo")
    public BaseResponse<ClassVO> getClassVOById(@RequestParam("id") Long id) {
        if (id < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "非法id");
        }
        Class classInfo = classService.getById(id);
        return ResultUtil.success(classService.getClassVO(classInfo));
    }

    /**
     * 获取班级信息（未脱敏）
     *
     * @param id
     */
    @ApiOperation("根据id获取班级全部信息（管理员）")
    @ApiOperationSupport(order = 5)
    @ApiImplicitParam(name = "id", value = "班级id", dataType = "Long", dataTypeClass = Long.class)
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Class> getClassById(@RequestParam("id") Long id) {
        if (id < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "非法id");
        }
        return ResultUtil.success(classService.getById(id));
    }

    /**
     * 分页获取班级信息（脱敏）
     *
     * @param classQueryListRequest
     */
    @ApiOperation("分页获取班级脱敏信息")
    @ApiOperationSupport(order = 6)
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<ClassVO>> listClassVOByPage(@RequestBody ClassQueryListRequest classQueryListRequest) {
        if (classQueryListRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取分页信息
        Long current = classQueryListRequest.getCurrent();
        Long pageSize = classQueryListRequest.getPageSize();
        // 限制爬虫
        if (pageSize > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<Class> classPage = classService.page(new Page<>(current, pageSize),
                classService.getQueryWrapper(classQueryListRequest));
        Page<ClassVO> classVOPage = new Page<>(current, pageSize, classPage.getTotal());
        List<ClassVO> classVOList = classService.getClassVOList(classPage.getRecords());
        classVOPage.setRecords(classVOList);
        return ResultUtil.success(classVOPage);
    }

    /**
     * 分页获取班级信息（未脱敏）
     *
     * @param classQueryListRequest
     */
    @ApiOperation("分页获取班级全部信息（管理员）")
    @ApiOperationSupport(order = 7)
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<Class>> listClassByPage(@RequestBody ClassQueryListRequest classQueryListRequest) {
        if (classQueryListRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取分页信息
        Long current = classQueryListRequest.getCurrent();
        Long pageSize = classQueryListRequest.getPageSize();
        Page<Class> classPage = classService.page(new Page<>(current, pageSize),
                classService.getQueryWrapper(classQueryListRequest));
        return ResultUtil.success(classPage);
    }
}
