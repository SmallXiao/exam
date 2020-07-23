package com.alvis.exam.service.impl;

import com.alvis.exam.domain.statistics.ExamMainLog;
import com.alvis.exam.repository.ExamMainLogMapper;
import com.alvis.exam.service.ExamMainLogService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author sunxiao
 * @Date 2020/6/17
 **/
@Service
public class ExamMainLogServiceImpl extends BaseServiceImpl<ExamMainLog> implements ExamMainLogService {

    private final ExamMainLogMapper examMainLogMapper;


    public ExamMainLogServiceImpl(ExamMainLogMapper examMainLogMapper) {
        super(examMainLogMapper);
        this.examMainLogMapper = examMainLogMapper;
    }


    @Override
    public void save(ExamMainLog examMainLog) {
        examMainLog.setCreateTime(new Date());
        examMainLogMapper.insert(examMainLog);
    }
}
