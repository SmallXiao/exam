package com.alvis.exam.viewmodel.api.statistics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author sunxiao
 * @Date 2020/7/29
 **/
@Getter
@Setter
public class UserAnswerReportRequestVM implements Serializable {

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "开始时间，格式：yyyyMMdd")
    private Integer startDate;

    @ApiModelProperty(value = "结束时间，格式：yyyyMMdd")
    private Integer endDate;



}
