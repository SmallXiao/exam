package com.alvis.exam.viewmodel.api.exam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author sunxiao
 * @Date 2020/6/17
 **/
@Getter
@Setter
public class ExamSubLogVM {

    @ApiModelProperty(value = "题库ID")
    private Integer subjectId;

    @ApiModelProperty(value = "题库ID")
    private Integer questionId;

    @ApiModelProperty(value = "题库ID")
    private String answer;

    @ApiModelProperty(value = "题库ID")
    private Integer iscorrect;
}
