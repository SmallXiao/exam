package com.alvis.exam.viewmodel.api.statistics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author sunxiao
 * @Date 2020/6/17
 **/
@Getter
@Setter
public class MonthlyReportResponseVM extends ReportResponseVM {

    @ApiModelProperty(value = "挑战次数")
    private Integer changeCount;

    @ApiModelProperty(value = "累计错题量")
    private Integer sumErrorCount;

    @ApiModelProperty(value = "平均错题量")
    private String avgErrorCount;

    @ApiModelProperty(value = "部门名")
    private String deptName;



}
