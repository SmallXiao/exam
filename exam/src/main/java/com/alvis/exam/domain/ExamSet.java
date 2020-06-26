package com.alvis.exam.domain;

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
public class ExamSet implements Serializable {

    private Integer id;

    // 单次答对题目数
    private Integer perCorrectCount;

    // 单个题目答题时间（秒）
    private Integer perQuestionTime;

    // 考察范围
    private String scope;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;
}
