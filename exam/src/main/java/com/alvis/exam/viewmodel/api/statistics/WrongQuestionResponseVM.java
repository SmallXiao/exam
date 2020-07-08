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

    @ApiModelProperty(value = "错题量")
    private Integer errorCount;

    @ApiModelProperty(value = "总量")
    private Integer sumCount;


}
