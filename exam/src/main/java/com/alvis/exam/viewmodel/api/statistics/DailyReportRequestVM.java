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
public class DailyReportRequestVM extends ReportRequestVM {

    @ApiModelProperty(value = "统计时间，格式：yyyyMMdd")
    private Integer userId;

    @ApiModelProperty(value = "统计时间，格式：yyyyMMdd")
    private Integer date;
}
