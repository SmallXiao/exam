package com.alvis.exam.service;

import com.alvis.exam.viewmodel.api.statistics.*;

import java.util.List;

public interface StatisticsService {


    /**
     * 每日挑战答题情况
     * @param requestVM
     */
    List<ReportResponseVM> dailyReport(DailyReportRequestVM requestVM);


    List<MonthlyReportResponseVM> monthlyReport(MonthlyReportRequestVM reportRequestVM);

    /**
     * 部门报表
     * @param reportRequestVM
     * @return
     */
    List deptReport(DeptReportRequestVM reportRequestVM);

    List<WrongQuestionResponseVM> wrongQuestionReport(WrongQuestionRequestVM reportRequestVM);



    List<DailyRankResponseVM> getUserDailyRankList(int date);
}
