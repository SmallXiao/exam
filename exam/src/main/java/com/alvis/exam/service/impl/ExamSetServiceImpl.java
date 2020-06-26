package com.alvis.exam.service.impl;

import com.alvis.exam.domain.ExamSet;
import com.alvis.exam.repository.ExamSetMapper;
import com.alvis.exam.service.ExamSetService;
import com.alvis.exam.viewmodel.admin.examset.ExamSetItemVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author sunxiao
 * @Date 2020/6/16
 **/
@Service
public class ExamSetServiceImpl extends BaseServiceImpl<ExamSet> implements ExamSetService {


    private final ExamSetMapper examSetMapper;

    @Autowired
    public ExamSetServiceImpl(ExamSetMapper examSetMapper) {
        super(examSetMapper);
        this.examSetMapper = examSetMapper;
    }


    @Override
    public ExamSet select(int id) {
        return examSetMapper.selectByPrimaryKey(id);
    }

    @Override
    public void edit(ExamSet model) {

        examSetMapper.updateByPrimaryKeySelective(model);

    }
}
