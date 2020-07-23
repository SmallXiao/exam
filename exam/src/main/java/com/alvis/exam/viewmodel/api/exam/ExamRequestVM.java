package com.alvis.exam.viewmodel.api.exam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author sunxiao
 * @Date 2020/7/23
 **/
@Getter
@Setter
@ApiModel(value = "ExamMainLog", description = "日志主表")
public class ExamRequestVM implements Serializable {

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

    @ApiModelProperty(value = "题目列表")
    private List<ExamSubRequestVM> list;

}
