package com.runde.signinbackend.model.dto.CheckRecord;

import com.runde.signinbackend.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CheckRecordQueryListRequest extends PageRequest {
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
     * 是否删除（0 - 未删除，1 - 删除）
     */
    @ApiModelProperty("是否删除（0 - 未删除，1 - 删除）")
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}
