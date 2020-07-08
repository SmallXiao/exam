package com.alvis.exam.viewmodel.api.statistics;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunxiao
 * @Date 2020/7/8
 **/
public class WrongQuestionRequestVM extends ReportRequestVM {

    @ApiModelProperty(value = "开始时间，格式：yyyyMMdd")
    private Integer startDate;

    @ApiModelProperty(value = "结束时间，格式：yyyyMMdd")
    private Integer endDate;
}
