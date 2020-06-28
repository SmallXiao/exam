package com.alvis.exam.viewmodel.admin.examset;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author sunxiao
 * @Date 2020/6/16
 **/
@Getter
@Setter
public class ExamSetItemVM {

    private Integer id;

    // 单次答对题目数
    private Integer perCorrectCount;

    // 单个题目答题时间（秒）
    private Integer perQuestionTime;

    // 考试范围
    private String scope;

    private Integer updateUser;

    private Date updateTime;

}
