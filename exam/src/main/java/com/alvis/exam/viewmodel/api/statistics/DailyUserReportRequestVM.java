package com.alvis.exam.viewmodel.api.statistics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author sunxiao
 * @Date 2020/8/4
 **/
@Getter
@Setter
public class DailyUserReportRequestVM implements Serializable {

    @ApiModelProperty(value = "查询时间，格式：yyyyMMdd")
    private Integer date;

    @ApiModelProperty(value = "查询用户名")
    private String userName;

}
