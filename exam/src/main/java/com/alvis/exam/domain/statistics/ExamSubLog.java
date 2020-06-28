package com.alvis.exam.domain.statistics;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author sunxiao
 * @Dte 2020/6/16
 **/
@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@ApiModel(value = "ExamSubLog", description = "日志从表")
public class ExamSubLog implements Serializable {

    private Integer mainId;

    @ApiModelProperty(value = "题库ID")
    private Integer subjectId;

    @ApiModelProperty(value = "问题ID")
    private Integer questionId;

    @ApiModelProperty(value = "用户回答")
    private String answer;

    @ApiModelProperty(value = "是否正确（1：正确 0：错误）")
    private Integer iscorrect;

}
