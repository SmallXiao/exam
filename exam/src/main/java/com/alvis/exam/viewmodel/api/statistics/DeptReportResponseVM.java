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
public class DeptReportResponseVM implements Serializable {

    private String deptName;

    @ApiModelProperty(value = "平均错题数")
    private Integer avgErrorCount;

    @ApiModelProperty(value = "平均花费时间")
    private Integer avgSpendTime;

}
