package com.alvis.exam.viewmodel.api.statistics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;



/**
 * @Author sunxiao
 * @Date 2020/6/17
 **/
@Getter
@Setter
public class ReportRequestVM {

    @ApiModelProperty(value = "统计时间，格式：yyyyMMdd")
    private Integer date;

    @ApiModelProperty(value = "开始时间")
    private Integer startDate;

    @ApiModelProperty(value = "结束时间")
    private Integer endDate;

    @ApiModelProperty(value = "搜索姓名")
    private String userName;

    @ApiModelProperty(value = "搜索部门名称")
    private String deptName;

    private Integer pageIndex = 1;

    private Integer pageSize = 10;

}
