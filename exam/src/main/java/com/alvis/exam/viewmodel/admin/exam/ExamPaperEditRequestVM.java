package com.alvis.exam.viewmodel.admin.exam;

import lombok.Data;

import java.util.List;

@Data
public class ExamPaperEditRequestVM {
    private Integer id;

    private Integer level;

    private Integer subjectId;

    private Integer paperType;

    private String name;
    private String supplier;
    private String paperName;

    private Integer suggestTime;

    private List<String> limitDateTime;

    private List<ExamPaperTitleItemVM> titleItems;

    private String score;

}
