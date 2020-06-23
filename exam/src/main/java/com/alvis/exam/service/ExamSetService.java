package com.alvis.exam.service;

import com.alvis.exam.domain.ExamSet;

public interface ExamSetService extends BaseService<ExamSet> {

    ExamSet select(int id);

    void edit(ExamSet model);

}
