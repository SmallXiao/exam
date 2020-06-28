package com.alvis.exam.viewmodel.api.exam;

import com.alvis.exam.domain.statistics.ExamSubLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @Author sunxiao
 * @Date 2020/6/17
 **/
@Getter
@Setter
@ApiModel(value = "ExamSubLog", description = "日志从表")
public class ExamMainLogVM {

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "耗时（秒）")
    private Integer spendTime;

    @ApiModelProperty(value = "答题状态（1：有效 0：无效）")
    private Integer status;

    @ApiModelProperty(value = "错题个数")
    private Integer errorCount;

    @ApiModelProperty(value = "从表")
    private List<ExamSubLog> list;



}
