package com.runde.signinbackend.model.dto.classInfo;

import com.runde.signinbackend.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class ClassQueryListRequest extends PageRequest implements Serializable {

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
