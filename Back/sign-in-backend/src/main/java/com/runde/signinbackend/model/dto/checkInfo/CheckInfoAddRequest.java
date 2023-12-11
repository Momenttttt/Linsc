package com.runde.signinbackend.model.dto.checkInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  签到信息添加请求
 * @author felix
 */
@Data
public class CheckInfoAddRequest implements Serializable {

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
    private static final long serialVersionUID = 1L;
}
