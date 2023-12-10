package com.runde.signinbackend.model.dto.checkInfo;

import com.runde.signinbackend.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author felix
 * @Description 签到信息查询多个请求
 * @date 2023/12/9
 * @version 1.0
 */
@Data
public class CheckInfoQueryListRequest extends PageRequest implements Serializable {

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
     * 创建者
     */
    @ApiModelProperty("创建者")
    private Long createUser;
    /**
     * 是否删除（0 - 未删除（默认），1 - 删除）
     */
    @ApiModelProperty("是否删除（0 - 未删除（默认），1 - 删除）")
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}
