package com.runde.signinbackend.model.dto.classInfo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ClassUpdateRequest implements Serializable {

    @ApiModelProperty(value = "id",required = true)
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

    private static final long serialVersionUID = 1L;
}
