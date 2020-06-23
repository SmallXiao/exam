package com.alvis.exam.service;

import com.alvis.exam.viewmodel.api.statistics.MonthlyReportResponseVM;
import com.alvis.exam.viewmodel.api.statistics.ReportRequestVM;
import com.alvis.exam.viewmodel.api.statistics.ReportResponseVM;
import com.alvis.exam.viewmodel.api.statistics.WrongQuestionResponseVM;

import java.util.List;

public interface StatisticsService {


    /**
     * 每日挑战答题情况
     * @param reportRequestVM
     */
    List<ReportResponseVM> dailyReport(ReportRequestVM reportRequestVM);


    List<MonthlyReportResponseVM> monthlyReport(ReportRequestVM reportRequestVM);

    /**
     * 部门报表
     * @param reportRequestVM
     * @return
     */
    List deptReport(ReportRequestVM reportRequestVM);

    List<WrongQuestionResponseVM> wrongQuestionReport(ReportRequestVM reportRequestVM);

}
