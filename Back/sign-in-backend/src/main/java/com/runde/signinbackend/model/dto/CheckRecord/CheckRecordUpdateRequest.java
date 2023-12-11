package com.runde.signinbackend.model.dto.CheckRecord;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CheckRecordUpdateRequest {
    /**
     * id
     */
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

    private static final long serialVersionUID = 1L;
}
