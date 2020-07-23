package com.alvis.exam.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alvis.exam.domain.ExamSet;
import com.alvis.exam.domain.User;
import com.alvis.exam.domain.statistics.ExamMainLog;
import com.alvis.exam.domain.statistics.ExamSubLog;
import com.alvis.exam.service.*;
import com.alvis.exam.viewmodel.api.exam.ExamRequestVM;
import com.alvis.exam.viewmodel.api.question.QuestionVM;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    private final UserService userService;

    private final ITQuestionService itQuestionService;

    public ExamServiceImpl(ExamMainLogService examMainLogService, ExamSubLogService examSubLogService, UserService userService, ITQuestionService itQuestionService) {
        this.examMainLogService = examMainLogService;
        this.examSubLogService = examSubLogService;
        this.userService = userService;
        this.itQuestionService = itQuestionService;
    }

    @Override
    @Transactional
    public void save(ExamRequestVM examRequest) {
        Instant instant = examRequest.getStartTime().toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        // 日志主表
        ExamMainLog examMainLog = new ExamMainLog();
        examMainLog.setYear(localDateTime.getYear());
        examMainLog.setMonth(localDateTime.getMonthValue());
        examMainLog.setDay(localDateTime.getDayOfMonth());
        examMainLog.setUserId(examRequest.getUserId());

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        //格式化字符串
        String dateStr = localDateTime.format(format);
        examMainLog.setDate(Integer.parseInt(dateStr));
        examMainLog.setStartTime(examRequest.getStartTime());
        examMainLog.setEndTime(examRequest.getEndTime());
        examMainLog.setSpendTime(examRequest.getSpendTime());
        examMainLog.setStatus(examRequest.getStatus());
        examMainLog.setErrorCount(examRequest.getErrorCount());

        User user = userService.getUserById(examRequest.getUserId());
        examMainLog.setUserName(user.getRealName());

        examMainLogService.save(examMainLog);

        // 日志从表
        examRequest.getList().forEach(item -> {
            ExamSubLog examSubLog = new ExamSubLog();
            BeanUtil.copyProperties(item, examSubLog);
            examSubLog.setMainId(examMainLog.getMainId());
            examSubLog.setDate(Integer.parseInt(localDateTime.format(format)));
            examSubLog.setUserName(user.getRealName());

            examSubLogService.save(examSubLog);
        });

    }

    @Override
    public List<QuestionVM> selectRandomList(ExamSet examSet) {

        List subjectIdList = Arrays.asList(examSet.getScope().split(","));


        return itQuestionService.selectRandomList(subjectIdList, examSet.getPerCorrectCount());

    }
}
