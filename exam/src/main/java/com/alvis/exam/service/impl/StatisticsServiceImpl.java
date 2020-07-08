package com.alvis.exam.service.impl;

import com.alvis.exam.repository.StatisticsMapper;
import com.alvis.exam.service.StatisticsService;
import com.alvis.exam.viewmodel.api.statistics.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sunxiao
 * @Date 2020/6/17
 **/
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public List<ReportResponseVM> dailyReport(DailyReportRequestVM requestVM) {

        return statisticsMapper.dailyReport(requestVM);
    }

    @Override
    public List<MonthlyReportResponseVM> monthlyReport(MonthlyReportRequestVM reportRequestVM) {

        return statisticsMapper.monthlyReport(reportRequestVM);
    }

    @Override
    public List deptReport(DeptReportRequestVM reportRequestVM) {


        return statisticsMapper.deptReport(reportRequestVM);
    }

    @Override
    public List<WrongQuestionResponseVM> wrongQuestionReport(WrongQuestionRequestVM reportRequestVM) {

        return statisticsMapper.wrongQuestionReport(reportRequestVM);
    }
}
