package com.alvis.exam.viewmodel.api.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author sunxiao
 * @Date 2020/6/30
 **/
@Getter
@Setter
@ApiModel(value = "Subject", description = "套题")
public class SubjectVM {

    @ApiModelProperty(value = "应答对题目数")
    private int correctCount;

    @ApiModelProperty(value = "答题时间")
    private int answerTime;

    @ApiModelProperty(value = "问题列表")
    private List<QuestionVM> list;
}
