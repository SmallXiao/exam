package com.alvis.exam.viewmodel.admin.question;

import com.alvis.exam.base.BasePage;
import lombok.Data;

@Data
public class QuestionPageRequestVM extends BasePage {

    private Integer id;
    private Integer level;
    private Integer subjectId;
    private Integer questionType;

    private String subjectName;
    private String paperName;
    private String questionName;

    private Integer startIndex;


}
