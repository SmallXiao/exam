package com.alvis.exam.viewmodel.api.statistics;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "题干")
    private String questionName;

    @ApiModelProperty(value = "所属套题")
    private String subjectName;

    @ApiModelProperty(value = "错题量")
    private Integer errorCount;

    @ApiModelProperty(value = "总答题量")
    private Integer sumCount;


}
