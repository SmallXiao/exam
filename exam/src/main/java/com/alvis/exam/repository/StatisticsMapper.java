package com.alvis.exam.repository;

import com.alvis.exam.viewmodel.api.statistics.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsMapper {


    List<ReportResponseVM> dailyReport(DailyReportRequestVM requestVM);

    List<MonthlyReportResponseVM> monthlyReport(MonthlyReportRequestVM reportRequestVM);

    List<DeptReportResponseVM> deptReport(DeptReportRequestVM reportRequestVM);


    List<WrongQuestionResponseVM> wrongQuestionReport(WrongQuestionRequestVM reportRequestVM);

    List<Integer> userAnswerDateReport(UserAnswerReportRequestVM requestVM);
    /**
     * 用户排名列表
     * @param date
     * @return
     */
    List<DailyRankResponseVM> getUserDailyRankList(int date);

}
