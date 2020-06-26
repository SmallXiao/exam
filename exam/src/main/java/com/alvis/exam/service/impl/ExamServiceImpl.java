package com.alvis.exam.service.impl;

import com.alvis.exam.domain.statistics.ExamMainLog;
import com.alvis.exam.domain.statistics.ExamSubLog;
import com.alvis.exam.service.ExamMainLogService;
import com.alvis.exam.service.ExamService;
import com.alvis.exam.service.ExamSubLogService;
import com.alvis.exam.viewmodel.api.exam.ExamMainLogVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author sunxiao
 * @Date 2020/6/16
 **/
@Service
public class ExamServiceImpl implements ExamService {

    private final ExamMainLogService examMainLogService;

    private final ExamSubLogService examSubLogService;

    public ExamServiceImpl(ExamMainLogService examMainLogService, ExamSubLogService examSubLogService) {
        this.examMainLogService = examMainLogService;
        this.examSubLogService = examSubLogService;
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
}
