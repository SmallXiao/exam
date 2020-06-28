package com.alvis.exam.viewmodel.api.statistics;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author sunxiao
 * @Date 2020/6/18
 **/
@Getter
@Setter
public class WrongQuestionResponseVM implements Serializable {


    private Integer errorCount;

    private Integer sumCount;


}
