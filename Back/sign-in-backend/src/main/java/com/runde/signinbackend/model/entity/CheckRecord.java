package com.runde.signinbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 签到记录(SgSigninRecord)实体类
 *
 * @author felix
 * @since 2023-12-10 15:16:52
 */
@TableName(value ="sg_signin_record")
@Data
public class CheckRecord implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 签到者
     */
    @ApiModelProperty("签到者")
    private Long signinUser;
    /**
     * 地理位置
     */
    @ApiModelProperty("地理位置")
    private String location;
    /**
     * 状态（0 - 缺勤（默认），1 - 出勤，2 - 请假，3 - 补签）
     */
    @ApiModelProperty("状态（0 - 缺勤（默认），1 - 出勤，2 - 请假，3 - 补签）")
    private Integer status;
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
     * 是否删除（0 - 未删除，1 - 删除）
     */
    @TableLogic
    @ApiModelProperty("是否删除（0 - 未删除（默认），1 - 删除）")
    private Integer isDelete;

}

