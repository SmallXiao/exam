package com.alvis.exam.service;

import com.alibaba.fastjson.JSONObject;
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

    /**
     * 错题统计
     * @param reportRequestVM
     * @return
     */
    List<WrongQuestionResponseVM> wrongQuestionReport(WrongQuestionRequestVM reportRequestVM);

    /**
     * 用户答题情况统计
     * @param requestVM
     * @return
     */
    JSONObject userAnswerDateReport(UserAnswerReportRequestVM requestVM);

    List<DailyRankResponseVM> getUserDailyRankList(int date);

    /**
     * 每日用户是否答题统计
     * @param requestVM
     * @return
     */
    JSONObject dailyUserReport(DailyUserReportRequestVM requestVM);



}
