package com.runde.signinbackend.model.dto.CheckRecord;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CheckRecordAddRequest {
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

    private static final long serialVersionUID = 1L;
}
