package com.alvis.exam.service.impl;

import com.alvis.exam.domain.statistics.ExamSubLog;
import com.alvis.exam.repository.ExamSubLogMapper;
import com.alvis.exam.service.ExamSubLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author sunxiao
 * @Date 2020/6/17
 **/
@Service
public class ExamSubLogServiceImpl extends BaseServiceImpl<ExamSubLog> implements ExamSubLogService {

    private final ExamSubLogMapper examSubLogMapper;

    @Autowired
    public ExamSubLogServiceImpl(ExamSubLogMapper examSubLogMapper) {
        super(examSubLogMapper);
        this.examSubLogMapper = examSubLogMapper;
    }


    @Override
    public void save(ExamSubLog examSubLog) {
        examSubLogMapper.insert(examSubLog);
    }
}
