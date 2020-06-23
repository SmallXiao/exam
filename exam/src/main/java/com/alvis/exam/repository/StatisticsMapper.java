package com.alvis.exam.repository;

import com.alvis.exam.viewmodel.api.statistics.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsMapper {


    List<ReportResponseVM> dailyReport(ReportRequestVM reportRequestVM);

    List<MonthlyReportResponseVM> monthlyReport(ReportRequestVM reportRequestVM);

    List<DeptReportResponseVM> deptReport(ReportRequestVM reportRequestVM);


    List<WrongQuestionResponseVM> wrongQuestionReport(ReportRequestVM reportRequestVM);

}
