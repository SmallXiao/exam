package com.alvis.exam.service.impl;

import com.alvis.exam.domain.ExamSet;
import com.alvis.exam.domain.statistics.ExamMainLog;
import com.alvis.exam.domain.statistics.ExamSubLog;
import com.alvis.exam.service.*;
import com.alvis.exam.viewmodel.api.exam.ExamMainLogVM;
import com.alvis.exam.viewmodel.api.question.QuestionVM;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

/**
 * @Author sunxiao
 * @Date 2020/6/16
 **/
@Service
public class ExamServiceImpl implements ExamService {

    private final ExamMainLogService examMainLogService;

    private final ExamSubLogService examSubLogService;

    private final ExamSetService examSetService;

    private final ITQuestionService itQuestionService;

    public ExamServiceImpl(ExamMainLogService examMainLogService, ExamSubLogService examSubLogService, ExamSetService examSetService, ITQuestionService itQuestionService) {
        this.examMainLogService = examMainLogService;
        this.examSubLogService = examSubLogService;
        this.examSetService = examSetService;
        this.itQuestionService = itQuestionService;
    }

    @Override
    @Transactional
    public void save(ExamMainLogVM examMainLogVM) {
        Instant instant = examMainLogVM.getStartTime().toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        // 日志主表
        ExamMainLog examMainLog = new ExamMainLog();
        examMainLog.setYear(localDateTime.getYear());
        examMainLog.setMonth(localDateTime.getMonthValue());
        examMainLog.setDay(localDateTime.getDayOfMonth());
        examMainLog.setUserId(examMainLogVM.getUserId());
        examMainLog.setStartTime(examMainLogVM.getStartTime());
        examMainLog.setEndTime(examMainLogVM.getEndTime());
        examMainLog.setSpendTime(examMainLogVM.getSpendTime());
        examMainLog.setStatus(examMainLogVM.getStatus());
        examMainLog.setErrorCount(examMainLogVM.getErrorCount());
        examMainLogService.save(examMainLog);

        // 日志从表
        List<ExamSubLog> examSubLogList = examMainLogVM.getList();
        examSubLogList.forEach(item -> {
            item.setMainId(examMainLog.getMainId());
            examSubLogService.save(item);
        });

    }

    @Override
    public List<QuestionVM> selectRandomList(ExamSet examSet) {

        List subjectIdList = Arrays.asList(examSet.getScope().split(","));


        return itQuestionService.selectRandomList(subjectIdList, examSet.getPerCorrectCount());

    }
}
