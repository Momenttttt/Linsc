package com.runde.signinbackend.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.runde.signinbackend.common.ErrorCode;
import com.runde.signinbackend.constant.CommonConstant;
import com.runde.signinbackend.constant.UserConstant;
import com.runde.signinbackend.exception.BusinessException;
import com.runde.signinbackend.mapper.UserMapper;
import com.runde.signinbackend.model.dto.user.UserLoginRequest;
import com.runde.signinbackend.model.dto.user.UserQueryListRequest;
import com.runde.signinbackend.model.dto.user.UserQueryOneRequest;
import com.runde.signinbackend.model.dto.user.UserRegisterRequest;
import com.runde.signinbackend.model.entity.User;
import com.runde.signinbackend.model.enums.UserRoleEnum;
import com.runde.signinbackend.model.vo.UserVO;
import com.runde.signinbackend.service.UserService;
import com.runde.signinbackend.utils.SqlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 最紧要开心
 * @description 针对表【sg_user(用户)】的数据库操作Service实现
 * @createDate 2023-11-02 10:38:00
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public Long register(UserRegisterRequest userRegisterRequest) {
        String account = userRegisterRequest.getAccount();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        // 三者都不为空
        if (StringUtils.isAnyBlank(account, password, checkPassword)) {
            if (StringUtils.isBlank(account)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号不能为空");
            }
            if (StringUtils.isBlank(password)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不能为空");
            }
            if (StringUtils.isBlank(checkPassword)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "二次输入密码不能为空");
            }
        }
        // 校验账号规则
        if (!account.matches("^[a-zA-Z0-9_]{8,15}$")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号为 8-15 位，支持字母、数字、下划线");
        }
        // 校验密码规则
        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,20}$")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码为 8-20 位，至少一个大写字符，一个小写字符和一个数字，不包含特殊字符");
        }
        // 两次输入的密码是否一致
        if (!StringUtils.equals(password, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "第二次输入的密码与第一次不一致");
        }
        synchronized (account.intern()) {
            Long count = this.query().eq("account", account).count();
            if (count > 0) {
                throw new BusinessException(ErrorCode.OPERATION_FAILED_ERROR, "账号已存在");
            }
            User user = new User();
            user.setAccount(account);
            user.setPassword(password);
            boolean result = this.save(user);
            if (!result) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            return user.getId();
        }
    }

    @Override
    public UserVO login(HttpServletRequest request, UserLoginRequest userLoginRequest) {
        String account = userLoginRequest.getAccount();
        String password = userLoginRequest.getPassword();
        // 账号或密码不能为空
        if (StringUtils.isAnyBlank(account, password)) {
            if (StringUtils.isBlank(account)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号不能为空");
            }
            if (StringUtils.isBlank(password)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不能为空");
            }
        }
        // 校验账号或密码是否符合规则
        if (!account.matches("^[a-zA-Z0-9_]{8,15}$")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
        }
        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,20}$")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        // 账号是否存在
        User user = this.query().eq("account", account).one();
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "账号不存在");
        }
        // 密码是否正确
        if (!StringUtils.equals(password, user.getPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        // 账号是否被封
        if (user.getStatus().equals(0)) {
            throw new BusinessException(ErrorCode.FORBIDENT_ERROR, "账号已被封");
        }
        // 记录用户登录态
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
        return this.getUserVO(user);
    }

    @Override
    public UserVO userLoginByWxMiniapp(WxMaJscode2SessionResult sessionResult, WxMaUserInfo userInfo, HttpServletRequest request) {
        String unionId = sessionResult.getUnionid();
        String openid = sessionResult.getOpenid();
        synchronized (unionId.intern()) {
            // 1.查询用户是否存在
            User user = this.query().eq("union_id", unionId).one();
            // 2.查询用户是否被封禁
            if (user != null && user.getStatus() == 0) {
                throw new BusinessException(ErrorCode.FORBIDENT_ERROR, "该用户已被封，禁止登录");
            }
            // 3.账户未存在则创建账户
            if (user == null) {
                user = new User();
                user.setUnionId(unionId);
                user.setOpenId(openid);
                user.setAvatar(userInfo.getAvatarUrl());
                user.setSex(Integer.valueOf(userInfo.getGender()));
                user.setName(userInfo.getNickName());
                boolean result = this.save(user);
                if (!result) {
                    throw new BusinessException(ErrorCode.SYSTEM_ERROR, "登录失败");
                }
            }
            // 4.记录用户登录态
            request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
            return getUserVO(user);
        }
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        if (user == null || user.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Long userId = user.getId();
        user = this.getById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        if (user.getStatus() == 0) {
            throw new BusinessException(ErrorCode.FORBIDENT_ERROR, "用户已被封");
        }
        return user;
    }

    @Override
    public Boolean userLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE) == null) {
           throw new BusinessException(ErrorCode.OPERATION_FAILED_ERROR, "未登录");
        };
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        return true;
    }

    @Override
    public void validUserParams(User user, Boolean add) {
        String account = user.getAccount();
        String password = user.getPassword();
        String unionId = user.getUnionId();
        String openId = user.getOpenId();
        String avatar = user.getAvatar();
        String name = user.getName();
        String personalSgn = user.getPersonalSgn();
        String role = user.getRole();
        Integer sex = user.getSex();

        // 如果是添加请求
        if (add) {
            if (StringUtils.isAnyBlank(account, password, unionId, openId)) {
                if (StringUtils.isBlank(account)) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号不能为空");
                }
                if (StringUtils.isBlank(password)) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不能为空");
                }
                if (StringUtils.isBlank(unionId)) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "unionId 不能为空");
                }
                if (StringUtils.isBlank(openId)) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "openId 不能为空");
                }
            }
        }

        if (StringUtils.isNotBlank(account) && !account.matches("^[a-zA-Z0-9_]{8,15}$")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号为 8-15 位，支持字母、数字、下划线");
        }
        if (StringUtils.isNotBlank(password) && !password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,20}$")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码为 8-20 位，至少一个大写字符，一个小写字符和一个数字，不包含特殊字符");
        }
        if (StringUtils.isNotBlank(avatar) && avatar.length() > 500) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片url超出长度限制");
        }
        if (StringUtils.isNotBlank(name)) {
            if (name.length() > 16) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "昵称不能超过16个字符");
            }
            if (name.matches("(^\\s)?.+(\\s$)?")) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "昵称不能以空白字符开头或结尾");
            }
        }
        if (StringUtils.isNotBlank(personalSgn) && personalSgn.length() > 60) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "个性签名不能超出60个字符");
        }
        if (StringUtils.isNotBlank(role) && UserRoleEnum.getEnumByValue(role.toLowerCase()) == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "只可设置 admin 或 user 身份");
        }
        if (sex != null && (sex != 0 && sex != 1)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "只可设置 0(女) 或 1(男) 性别");
        }
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryListRequest userQueryListRequest) {
        if (userQueryListRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = userQueryListRequest.getName();
        Integer sex = userQueryListRequest.getSex();
        String personalSgn = userQueryListRequest.getPersonalSgn();
        Integer status = userQueryListRequest.getStatus();
        String role = userQueryListRequest.getRole();
        Integer isDelete = userQueryListRequest.getIsDelete();
        String sortField = userQueryListRequest.getSortField();
        String sortOrder = userQueryListRequest.getSortOrder();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        queryWrapper.eq(sex != null, "sex", sex);
        queryWrapper.like(StringUtils.isNotBlank(personalSgn), "personal_sgn", personalSgn);
        queryWrapper.eq(status != null, "status", status);
        queryWrapper.eq(StringUtils.isNotBlank(role), "role", role);
        queryWrapper.eq(isDelete != null, "is_delete", isDelete);
        queryWrapper.orderBy(SqlUtil.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryOneRequest userQueryOneRequest) {
        if (userQueryOneRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String openId = userQueryOneRequest.getOpenId();
        String unionId = userQueryOneRequest.getUnionId();
        String account = userQueryOneRequest.getAccount();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(openId), "open_id", openId);
        queryWrapper.eq(StringUtils.isNotBlank(unionId), "union_id", unionId);
        queryWrapper.eq(StringUtils.isNotBlank(account), "account", account);
        return queryWrapper;
    }

    @Override
    public UserVO getUserVO(User user) {
        return user == null? null : BeanUtil.copyProperties(user, UserVO.class);
    }

    @Override
    public List<UserVO> getUserVOList(List<User> users) {
        if (CollectionUtil.isEmpty(users)) {
            return Collections.emptyList();
        }
        return users.stream().map(this::getUserVO).collect(Collectors.toList());
    }
}