package com.alvis.exam.viewmodel.api.statistics;

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

    private Integer title;

    private Integer subjectName;

    private Integer errorCount;

}
