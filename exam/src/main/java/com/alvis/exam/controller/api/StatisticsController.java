package com.alvis.exam.controller.api;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.service.StatisticsService;
import com.alvis.exam.viewmodel.api.statistics.*;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 统计相关
 * @Author sunxiao
 * @Date 2020/6/15
 **/
@RestController("StatisticsController")
@RequestMapping(value = "/api/statistics")
@AllArgsConstructor
@Api(tags = "Statistics", description = "统计")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * 每日挑战答题情况
     * @return
     */
    @PostMapping(value = "/dailyReport")
    public RestResponse dailyReport(@RequestBody DailyReportRequestVM requestVM) {

        List<ReportResponseVM> list = statisticsService.dailyReport(requestVM);
        return RestResponse.ok(list);
    }

    /**
     * 每月挑战答题情况
     * @return
     */
    @PostMapping(value = "/monthlyReport")
    public RestResponse monthlyReport(@RequestBody MonthlyReportRequestVM reportRequestVM) {

        List<MonthlyReportResponseVM> list = statisticsService.monthlyReport(reportRequestVM);
        return RestResponse.ok(list);
    }


    /**
     * 服务站综合排名
     */
    @PostMapping(value = "/deptReport")
    public RestResponse deptReport(@RequestBody DeptReportRequestVM reportRequestVM) {

        List<MonthlyReportResponseVM> list = statisticsService.deptReport(reportRequestVM);
        return RestResponse.ok(list);
    }

    /**
     * 错题报告
     */
    @PostMapping(value = "/wrongQuestionReport")
    public RestResponse wrongQuestionReport(@RequestBody WrongQuestionRequestVM reportRequestVM) {

        List<WrongQuestionResponseVM> list = statisticsService.wrongQuestionReport(reportRequestVM);
        return RestResponse.ok(list);
    }



}
