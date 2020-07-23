package com.alvis.exam.service;


import com.alvis.exam.domain.ExamSet;
import com.alvis.exam.viewmodel.api.exam.ExamMainLogVM;
import com.alvis.exam.viewmodel.api.exam.ExamRequestVM;
import com.alvis.exam.viewmodel.api.question.QuestionVM;

import java.util.List;

public interface ExamService {

    void save(ExamRequestVM examRequest);


    List<QuestionVM> selectRandomList(ExamSet examSet);
}
