package com.alvis.exam.domain.statistics;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author sunxiao
 * @Date 2020/6/16
 **/
@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@ApiModel(value = "ExamMainLog", description = "日志主表")
public class ExamMainLog implements Serializable {

    private Integer mainId;

    @ApiModelProperty(value = "年")
    private Integer year;

    @ApiModelProperty(value = "月")
    private Integer month;

    @ApiModelProperty(value = "日")
    private Integer day;

    @ApiModelProperty(value = "日期")
    private Integer date;

    @ApiModelProperty(value = "登录用户唯一标识")
    private Integer userId;

    @ApiModelProperty(value = "登录用户名")
    private String userName;

    @ApiModelProperty(value = "部门ID")
    private Integer deptId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "开始答题时间")
    private Date startTime;

    @ApiModelProperty(value = "结束答题时间")
    private Date endTime;

    @ApiModelProperty(value = "耗时（毫秒）")
    private Integer spendTime;

    @ApiModelProperty(value = "答题状态（1：有效 0：无效）")
    private Integer status;

    @ApiModelProperty(value = "错题个数")
    private Integer errorCount;

    private Date createTime;
}
