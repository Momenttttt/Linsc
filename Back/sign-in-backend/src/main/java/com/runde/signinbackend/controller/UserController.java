package com.runde.signinbackend.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.runde.signinbackend.annotation.AuthCheck;
import com.runde.signinbackend.common.BaseResponse;
import com.runde.signinbackend.common.ErrorCode;
import com.runde.signinbackend.constant.UserConstant;
import com.runde.signinbackend.exception.BusinessException;
import com.runde.signinbackend.model.dto.user.*;
import com.runde.signinbackend.model.entity.User;
import com.runde.signinbackend.model.vo.UserVO;
import com.runde.signinbackend.service.UserService;
import com.runde.signinbackend.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @BelongsProject sign-in-backend
 * @BelongsPackage com.runde.signinbackend.controller
 * @Author 最紧要开心
 * @CreateTime 2023/11/2 10:43
 * @Description 用户接口
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "1.用户模块")
public class UserController {

    @Resource
    private WxMaService wxMaService;

    @Resource
    private UserService userService;

    // region 登录注册相关

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     */
    @ApiOperation("用户注册")
    @ApiOperationSupport(order = 1)
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        Long id = userService.register(userRegisterRequest);
        return ResultUtil.success(id);
    }

    /**
     * 用户登录
     *
     * @param request
     * @param userLoginRequest
     */
    @ApiOperation("用户登录")
    @ApiOperationSupport(order = 2)
    @PostMapping("/login")
    public BaseResponse<UserVO> userLogin(HttpServletRequest request, @RequestBody UserLoginRequest userLoginRequest) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        return ResultUtil.success(userService.login(request, userLoginRequest));
    }

    /**
     * 微信小程序登录
     *
     * @param request
     * @param code
     * @param encryptedData
     * @param iv
     */
    @ApiOperation("微信小程序登录")
    @ApiOperationSupport(order = 3)
    @GetMapping("/login/wx_miniapp")
    public BaseResponse<UserVO> userLoginByWxMiniapp(HttpServletRequest request,
                                                     @RequestParam("code") String code,
                                                     @RequestParam("encryptedData") String encryptedData,
                                                     @RequestParam("iv") String iv) {
        try {
            WxMaJscode2SessionResult sessionResult = wxMaService.jsCode2SessionInfo(code);
            String openId = sessionResult.getOpenid();
            String unionId = sessionResult.getUnionid();
            if (StringUtils.isAnyBlank(unionId, openId)) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "登陆失败，系统错误");
            }
            String sessionKey = sessionResult.getSessionKey();
            WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
            return ResultUtil.success(userService.userLoginByWxMiniapp(sessionResult, userInfo, request));
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用户注销
     *
     * @param request
     */
    @ApiOperation("用户注销")
    @ApiOperationSupport(order = 4)
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        return ResultUtil.success(userService.userLogout(request));
    }

    /**
     * 获取登录用户
     *
     * @param request
     */
    @ApiOperation(value = "获取当前登录用户", produces = "application/json")
    @ApiOperationSupport(order = 5)
    @GetMapping("/get/login")
    public BaseResponse<UserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        return ResultUtil.success(userService.getUserVO(user));
    }

    // endregion

    // region 增删改查

    /**
     * 添加用户
     *
     * @param userAddRequest
     */
    @ApiOperation("添加用户（管理员）")
    @ApiOperationSupport(order = 6)
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest) {
        if (userAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = BeanUtil.copyProperties(userAddRequest, User.class);
        // 校验用户添加参数
        userService.validUserParams(user, true);
        boolean result = userService.save(user);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_FAILED_ERROR, "用户添加失败");
        }
        return ResultUtil.success(user.getId());
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @ApiOperation("删除用户（管理员）")
    @ApiOperationSupport(order = 7)
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "Long", dataTypeClass = Long.class)
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUser(@RequestParam("id") Long id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id不能为空");
        }
        boolean result = userService.removeById(id);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_FAILED_ERROR, "用户删除失败");
        }
        return ResultUtil.success(true);
    }

    /**
     * 修改用户
     *
     * @param userUpdateRequest
     */
    @ApiOperation("修改用户（管理员）")
    @ApiOperationSupport(order = 8)
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        if (userUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = BeanUtil.copyProperties(userUpdateRequest, User.class);
        // 校验用户修改参数
        userService.validUserParams(user, false);
        boolean result = userService.updateById(user);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_FAILED_ERROR, "用户修改失败");
        }
        return ResultUtil.success(true);
    }

    /**
     * 获取用户视图（脱敏）
     *
     * @param id
     */
    @ApiOperation("根据id获取用户脱敏信息")
    @ApiOperationSupport(order = 9)
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "Long", dataTypeClass = Long.class)
    @GetMapping("/get/vo")
    public BaseResponse<UserVO> getUserVOById(@RequestParam("id") Long id) {
        if (id < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "非法id");
        }
        User user = userService.getById(id);
        return ResultUtil.success(userService.getUserVO(user));
    }

    /**
     * 获取用户信息（未脱敏）
     *
     * @param id
     */
    @ApiOperation("根据id获取用户全部信息（管理员）")
    @ApiOperationSupport(order = 10)
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "Long", dataTypeClass = Long.class)
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<User> getUserById(@RequestParam("id") Long id) {
        if (id < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "非法id");
        }
        return ResultUtil.success(userService.getById(id));
    }

    /**
     * 根据条件获取用户信息（脱敏）
     *
     * @param userQueryOneRequest
     */
    @ApiOperation("查询指定用户脱敏信息")
    @ApiOperationSupport(order = 11)
    @PostMapping("/query/vo")
    public BaseResponse<UserVO> queryUserVO(@RequestBody UserQueryOneRequest userQueryOneRequest) {
        if (userQueryOneRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "查询条件不能为空");
        }
        User user = userService.getOne(userService.getQueryWrapper(userQueryOneRequest));
        return ResultUtil.success(userService.getUserVO(user));
    }

    /**
     * 根据条件获取用户信息（未脱敏）
     *
     * @param userQueryOneRequest
     */
    @ApiOperation("查询指定用户全部信息（管理员）")
    @ApiOperationSupport(order = 12)
    @PostMapping("/query")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<User> queryUser(@RequestBody UserQueryOneRequest userQueryOneRequest) {
        if (userQueryOneRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "查询条件不能为空");
        }
        return ResultUtil.success(userService.getOne(userService.getQueryWrapper(userQueryOneRequest)));
    }

    /**
     * 分页获取用户信息（脱敏）
     *
     * @param userQueryListRequest
     */
    @ApiOperation("分页获取用户脱敏信息")
    @ApiOperationSupport(order = 13)
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryListRequest userQueryListRequest) {
        if (userQueryListRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取分页信息
        Long current = userQueryListRequest.getCurrent();
        Long pageSize = userQueryListRequest.getPageSize();
        // 限制爬虫
        if (pageSize > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<User> userPage = userService.page(new Page<>(current, pageSize),
                userService.getQueryWrapper(userQueryListRequest));
        Page<UserVO> userVOPage = new Page<>(current, pageSize, userPage.getTotal());
        List<UserVO> userVOList = userService.getUserVOList(userPage.getRecords());
        userVOPage.setRecords(userVOList);
        return ResultUtil.success(userVOPage);
    }

    /**
     * 分页获取用户信息（未脱敏）
     *
     * @param userQueryListRequest
     */
    @ApiOperation("分页获取用户全部信息（管理员）")
    @ApiOperationSupport(order = 14)
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<User>> listUserByPage(@RequestBody UserQueryListRequest userQueryListRequest) {
        if (userQueryListRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取分页信息
        Long current = userQueryListRequest.getCurrent();
        Long pageSize = userQueryListRequest.getPageSize();
        Page<User> userPage = userService.page(new Page<>(current, pageSize),
                userService.getQueryWrapper(userQueryListRequest));
        return ResultUtil.success(userPage);
    }

    /**
     * 获取全部用户信息（未脱敏）
     *
     * @param userQueryListRequest
     */
    @ApiOperation("不分页获取用户全部信息（管理员）")
    @ApiOperationSupport(order = 15)
    @PostMapping("/list")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<User>> listUser(@RequestBody UserQueryListRequest userQueryListRequest) {
        if (userQueryListRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtil.success(userService.list(userService.getQueryWrapper(userQueryListRequest)));
    }

    /**
     * 用户修改个人信息
     *
     * @param userUpdateBySelfRequest
     */
    @ApiOperation("修改个人信息")
    @ApiOperationSupport(order = 16)
    @PostMapping("/update/self")
    public BaseResponse<Boolean> updatePersonalInformation(@RequestBody UserUpdateBySelfRequest userUpdateBySelfRequest,
                                                           HttpServletRequest request) {
        if (userUpdateBySelfRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = BeanUtil.copyProperties(userUpdateBySelfRequest, User.class);
        // 校验用户修改参数
        userService.validUserParams(user, false);
        // 获取当前登录用户id
        Long id = userService.getLoginUser(request).getId();
        user.setId(id);
        boolean result = userService.updateById(user);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_FAILED_ERROR, "更新个人信息失败");
        }
        return ResultUtil.success(true);
    }

    /**
     * 封禁用户
     *
     * @param id
     */
    @ApiOperation("封禁用户（管理员）")
    @ApiOperationSupport(order = 17)
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "Long", dataTypeClass = Long.class)
    @GetMapping("/ban")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> banUser(@RequestParam("id") Long id) {
        if (id < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "非法id");
        }
        User user = new User();
        user.setId(id);
        user.setStatus(0);
        boolean result = userService.updateById(user);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_FAILED_ERROR, "封禁用户失败");
        }
        return ResultUtil.success(true);
    }

    /**
     * 解封用户
     *
     * @param id
     */
    @ApiOperation("解封用户（管理员）")
    @ApiOperationSupport(order = 18)
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "Long", dataTypeClass = Long.class)
    @GetMapping("/unseal")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> unsealUser(@RequestParam("id") Long id) {
        if (id < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "非法id");
        }
        User user = new User();
        user.setId(id);
        user.setStatus(1);
        boolean result = userService.updateById(user);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_FAILED_ERROR, "解封用户失败");
        }
        return ResultUtil.success(true);
    }

    // endregion
}