package com.alvis.exam.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alvis.exam.domain.User;
import com.alvis.exam.repository.StatisticsMapper;
import com.alvis.exam.service.StatisticsService;
import com.alvis.exam.service.TExamMainLogService;
import com.alvis.exam.service.UserService;
import com.alvis.exam.viewmodel.api.statistics.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private UserService userService;

    @Override
    public List<ReportResponseVM> dailyReport(DailyReportRequestVM requestVM) {
        List<ReportResponseVM> result = statisticsMapper.dailyReport(requestVM);

        result = result.stream().filter(item -> {
            if (!StringUtils.isEmpty(requestVM.getUserName()) && !item.getUserName().equals(requestVM.getUserName())) {
                return false;
            }
            if (!StringUtils.isEmpty(requestVM.getDeptName()) && !item.getDeptName().equals(requestVM.getDeptName())) {
                return false;
            }

            return true;

        }).collect(Collectors.toList());
        return result;
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

    @Override
    public JSONObject dailyUserReport(DailyUserReportRequestVM requestVM) {


        DailyReportRequestVM request = new DailyReportRequestVM();
        request.setDate(requestVM.getDate());
        if (!StringUtils.isEmpty(requestVM.getUserName())) {
            request.setUserName(requestVM.getUserName());
        }
        List<ReportResponseVM> responseVM = statisticsMapper.dailyReport(request);

        // 已答题人
        Map<Integer, String> answerUser = responseVM.stream().collect(Collectors.toMap(ReportResponseVM::getUserId, ReportResponseVM::getUserName));
        // 未答题人
        List<User> noAnswerUserList = userService.getUsers();
        List<User> finalNoAnswerUserList = noAnswerUserList;
        answerUser.forEach((k, v) -> {
            Iterator<User> itr = finalNoAnswerUserList.iterator();
            while(itr.hasNext()) {
                if (itr.next().getId().equals(k)) {
                    itr.remove();
                    break;
                }
            }
        });

        if (!StringUtils.isEmpty(requestVM.getUserName())) {// 查询某人的答题情况
            noAnswerUserList = noAnswerUserList.stream().filter(item -> item.getUserName().equals(requestVM.getUserName())).collect(Collectors.toList());
        }

        JSONObject jsonObject = new JSONObject(5);
        jsonObject.put("answerUser", responseVM);
        jsonObject.put("noAnswerUser", noAnswerUserList);

        return jsonObject;
    }


}
