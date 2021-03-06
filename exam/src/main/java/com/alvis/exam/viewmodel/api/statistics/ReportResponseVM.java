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
public class ReportResponseVM implements Serializable {

    @ApiModelProperty(value = "排名")
    private Integer rank;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "答题时间，耗时（毫秒）")
    private String spendTime;

    @ApiModelProperty(value = "错题量")
    private Integer errorCount;

}
