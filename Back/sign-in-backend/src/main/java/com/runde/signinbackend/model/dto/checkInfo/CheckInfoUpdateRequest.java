package com.runde.signinbackend.model.dto.checkInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  签到信息更新请求
 *
 * @Auther: felix
 * @Date: 2023/12/10
 */
@Data
public class CheckInfoUpdateRequest implements Serializable {
    /**
     * id
     */
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

    private static final long serialVersionUID = 1L;
}
