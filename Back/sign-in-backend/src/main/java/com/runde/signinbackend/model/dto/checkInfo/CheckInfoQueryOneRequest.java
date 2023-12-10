package com.runde.signinbackend.model.dto.checkInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * @author felix
 * @Description 签到信息查询单请求
 * @date 2023/12/9
 * @version 1.0
 */
@Data
public class CheckInfoQueryOneRequest implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
    private static final long serialVersionUID = 1L;
}
