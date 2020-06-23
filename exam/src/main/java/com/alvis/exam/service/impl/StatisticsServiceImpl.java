package com.alvis.exam.service.impl;

import com.alvis.exam.repository.StatisticsMapper;
import com.alvis.exam.service.StatisticsService;
import com.alvis.exam.viewmodel.api.statistics.MonthlyReportResponseVM;
import com.alvis.exam.viewmodel.api.statistics.ReportRequestVM;
import com.alvis.exam.viewmodel.api.statistics.ReportResponseVM;
import com.alvis.exam.viewmodel.api.statistics.WrongQuestionResponseVM;
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
    public List<ReportResponseVM> dailyReport(ReportRequestVM reportRequestVM) {

        return statisticsMapper.dailyReport(reportRequestVM);
    }

    @Override
    public List<MonthlyReportResponseVM> monthlyReport(ReportRequestVM reportRequestVM) {

        return statisticsMapper.monthlyReport(reportRequestVM);
    }

    @Override
    public List deptReport(ReportRequestVM reportRequestVM) {


        return statisticsMapper.deptReport(reportRequestVM);
    }

    @Override
    public List<WrongQuestionResponseVM> wrongQuestionReport(ReportRequestVM reportRequestVM) {

        return statisticsMapper.wrongQuestionReport(reportRequestVM);
    }
}
