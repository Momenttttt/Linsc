package com.runde.signinbackend.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.runde.signinbackend.model.dto.user.UserLoginRequest;
import com.runde.signinbackend.model.dto.user.UserQueryListRequest;
import com.runde.signinbackend.model.dto.user.UserQueryOneRequest;
import com.runde.signinbackend.model.dto.user.UserRegisterRequest;
import com.runde.signinbackend.model.entity.User;
import com.runde.signinbackend.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 最紧要开心
* @description 针对表【sg_user(用户)】的数据库操作Service
* @createDate 2023-11-02 10:38:00
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     */
    Long register(UserRegisterRequest userRegisterRequest);

    /**
     * 账号密码登录
     *
     * @param request
     * @param userLoginRequest
     */
    UserVO login(HttpServletRequest request, UserLoginRequest userLoginRequest);

    /**
     * 微信小程序登录
     *
     * @param sessionResult
     * @param userInfo
     * @param request
     */
    UserVO userLoginByWxMiniapp(WxMaJscode2SessionResult sessionResult, WxMaUserInfo userInfo, HttpServletRequest request);

    /**
     * 获取用户视图（脱敏）
     *
     * @param user
     */
    UserVO getUserVO(User user);

    /**
     * 获取用户视图列表（脱敏）
     *
     * @param users
     */
    List<UserVO> getUserVOList(List<User> users);

    /**
     * 获取登录用户
     *
     * @param request
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request
     */
    Boolean userLogout(HttpServletRequest request);

    /**
     * 用户参数校验
     *
     * @param user
     * @param add
     */
    void validUserParams(User user, Boolean add);

    /**
     * 获取查询条件包装器
     *
     * @param userQueryListRequest
     */
    QueryWrapper<User> getQueryWrapper(UserQueryListRequest userQueryListRequest);

    /**
     * 获取查询条件包装器
     *
     * @param userQueryOneRequest
     */
    QueryWrapper<User> getQueryWrapper(UserQueryOneRequest userQueryOneRequest);
}