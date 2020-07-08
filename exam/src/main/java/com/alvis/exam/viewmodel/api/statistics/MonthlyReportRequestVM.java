package com.alvis.exam.viewmodel.api.statistics;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author sunxiao
 * @Date 2020/7/8
 **/
public class MonthlyReportRequestVM extends ReportRequestVM {

    @ApiModelProperty(value = "统计年")
    private Integer year;

    @ApiModelProperty(value = "统计月")
    private Integer month;
}
