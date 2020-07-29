package com.alvis.exam.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alvis.exam.repository.StatisticsMapper;
import com.alvis.exam.service.StatisticsService;
import com.alvis.exam.service.TExamMainLogService;
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

    @Autowired
    private TExamMainLogService tExamMainLogService;

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

    @Override
    public JSONObject userAnswerDateReport(UserAnswerReportRequestVM requestVM) {
        // 当前用户答题天数
        int count = tExamMainLogService.selectCountByUserId(requestVM.getUserId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalCount", count);

        //
        List<Integer> list = statisticsMapper.userAnswerDateReport(requestVM);
        jsonObject.put("list", list);

        return jsonObject;
    }

    @Override
    public List<DailyRankResponseVM> getUserDailyRankList(int date) {
        return statisticsMapper.getUserDailyRankList(date);
    }


}
