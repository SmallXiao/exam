package com.alvis.exam.viewmodel.api.statistics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author sunxiao
 * @Date 2020/7/8
 **/
@Getter
@Setter
public class MonthlyReportRequestVM extends ReportRequestVM {

    @ApiModelProperty(value = "统计年")
    private Integer year;

    @ApiModelProperty(value = "统计月")
    private Integer month;
}
