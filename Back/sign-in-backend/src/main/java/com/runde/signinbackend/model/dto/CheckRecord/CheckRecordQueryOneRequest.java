package com.runde.signinbackend.model.dto.CheckRecord;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CheckRecordQueryOneRequest {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    private static final long serialVersionUID = 1L;
}
