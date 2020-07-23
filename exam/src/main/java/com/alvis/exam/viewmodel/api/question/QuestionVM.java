package com.alvis.exam.viewmodel.api.question;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author sunxiao
 * @Date 2020/6/30
 **/
@Getter
@Setter
@ApiModel(value = "Question", description = "问题")
public class QuestionVM {

    @ApiModelProperty(value = "套题ID")
    private String subjectId;


    @ApiModelProperty(value = "题目ID")
    private String questionId;

    @ApiModelProperty(value = "题目")
    private String name;

    @ApiModelProperty(value = "提供者")
    private String supplier;

    @ApiModelProperty(value = "正确选项")
    private String correct;

    private String choose;

    @ApiModelProperty(value = "选项")
    private JSONObject option;


}
