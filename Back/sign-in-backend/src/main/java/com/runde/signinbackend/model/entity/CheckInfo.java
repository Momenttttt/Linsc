package com.runde.signinbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 签到信息(SgSigninInfo)实体类
 *
 * @author felix
 * @since 2023-12-09 18:44:11
 */
@TableName(value ="sg_signin_info")
@Data
public class CheckInfo implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 班级id
     */
    @ApiModelProperty("班级 id")
    private Long classId;
    /**
     * 签到简介
     */
    @ApiModelProperty("签到简介")
    private String profile;
    /**
     * 地理位置
     */
    @ApiModelProperty("地理位置")
    private String location;
    /**
     * 过期时间
     */
    @ApiModelProperty("过期时间")
    private Integer expireTime;
    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private Long createUser;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;
    /**
     * 是否删除（0 - 未删除（默认），1 - 删除）
     */
    @TableLogic
    @ApiModelProperty("是否删除（0 - 未删除（默认），1 - 删除）")
    private Integer isDelete;

}

