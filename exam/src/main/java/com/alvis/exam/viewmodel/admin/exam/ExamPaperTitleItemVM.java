package com.alvis.exam.viewmodel.admin.exam;

import com.alvis.exam.viewmodel.admin.question.QuestionEditRequestVM;
import lombok.Data;

import java.util.List;

@Data
public class ExamPaperTitleItemVM {

    private String name;

    private List<QuestionEditRequestVM> questionItems;
}
