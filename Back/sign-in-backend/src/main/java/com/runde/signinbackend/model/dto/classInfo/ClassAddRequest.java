package com.runde.signinbackend.model.dto.classInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClassAddRequest implements Serializable {

    /**
     * 班级名
     */
    @ApiModelProperty(value = "班级名", required = true)
    private String name;

    /**
     * 班级简介
     */
    @ApiModelProperty(value = "班级简介")
    private String profile;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", required = true)
    private String createUser;

    private static final long serialVersionUID = 1L;
}
