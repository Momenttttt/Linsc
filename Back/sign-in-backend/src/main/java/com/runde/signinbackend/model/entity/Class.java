package com.runde.signinbackend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName(value ="sg_class_info")
@Data
public class Class implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 班级名
     */
    @ApiModelProperty("班级名")
    private String name;

    /**
     * 班级简介
     */
    @ApiModelProperty("班级简介")
    private String profile;

    /**
     * 班级码
     */
    @ApiModelProperty("班级码")
    private String code;


    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private Integer createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("最近更新时间")
    private LocalDateTime updateTime;

    /**
     * 是否删除（0 - 未删除，1 - 删除）
     */
    @TableLogic
    @ApiModelProperty("是否删除（0 - 未删除，1 - 删除）")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
