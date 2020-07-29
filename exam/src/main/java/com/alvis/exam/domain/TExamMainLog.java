package com.alvis.exam.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author sunxiao
 * @Date 2020/7/29
 **/
@Getter
@Setter
public class TExamMainLog implements Serializable {

    private Integer mainId;

    private Integer year;

    private Integer month;

    private Integer day;

    private Integer date;

    private Integer userId;

    private String userName;

    private Integer deptId;

    private String deptName;

    private Date startTime;

    private Date endTime;

    private Integer spend_time;

    private Integer status;

    private Integer errorCount;

    private Date createTime;

}
