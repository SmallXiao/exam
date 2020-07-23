package com.alvis.exam.viewmodel.api.exam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author sunxiao
 * @Date 2020/7/23
 **/
@Getter
@Setter
public class ExamSubRequestVM implements Serializable {

    @ApiModelProperty(value = "题库ID")
    private Integer subjectId;

    @ApiModelProperty(value = "问题ID")
    private Integer questionId;

    @ApiModelProperty(value = "用户回答")
    private String answer;

    @ApiModelProperty(value = "是否正确（1：正确 0：错误）")
    private Integer iscorrect;
}
