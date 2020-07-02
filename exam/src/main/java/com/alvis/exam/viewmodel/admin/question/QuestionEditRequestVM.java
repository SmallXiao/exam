package com.alvis.exam.viewmodel.admin.question;

import lombok.Data;

import java.util.List;

@Data
public class QuestionEditRequestVM {

    private Integer id;

    private Integer questionType;

    private Integer subjectId;
    private String paperId;

    private String title;

    private Integer gradeLevel;


    private List<QuestionEditItemVM> items;

    private String analyze;

    private List<String> correctArray;

    private String correct;

    private String score;

    private Integer difficult;

    private Integer itemOrder;
}
