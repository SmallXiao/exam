package com.alvis.exam.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.ExamSet;
import com.alvis.exam.service.ExamService;
import com.alvis.exam.service.ExamSetService;
import com.alvis.exam.viewmodel.api.exam.ExamMainLogVM;
import com.alvis.exam.viewmodel.api.question.QuestionVM;
import com.alvis.exam.viewmodel.api.question.SubjectVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 考试设置相关
 * @Author sunxiao
 * @Date 2020/6/15
 **/
@RestController("ExamController")
@RequestMapping(value = "/api/exam")
@AllArgsConstructor
@Api(tags = "Exam", description = "考试")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamSetService examSetService;


    /**
     * 随机获取套题
     * @return
     */
    @GetMapping(value = "randomList")
    @ApiOperation("随机获取挑战题")
    public RestResponse<SubjectVM> randomList(@RequestParam(required = false) Integer count) {
        // 获取考察范围
        ExamSet examSet = examSetService.select(1);
        if (count == null) {
            count = examSet.getPerCorrectCount();
        }

        SubjectVM subjectVM = new SubjectVM();
        subjectVM.setCorrectCount(count);
        subjectVM.setAnswerTime(examSet.getPerQuestionTime());

        List<QuestionVM> list = examService.selectRandomList(examSet);
        list.forEach(item -> {
            String choose = item.getChoose();

            JSONObject jsonObject = new JSONObject(10);
            if (StringUtils.isEmpty(choose)) {// 为空说明是判断题
                jsonObject.put("A", "正确");
                jsonObject.put("B", "错误");

                String correct = "A";
                if ("错误".equals(item.getCorrect())) {
                    correct = "B";
                }
                item.setCorrect(correct);

            } else {// 选择题
                String A = StringUtils.substringBetween(choose,"A、","B、");
                String B = StringUtils.substringBetween(choose,"B、","C、");
                String C = StringUtils.substringBetween(choose,"C、","D、");
                String D = StringUtils.substringAfter(choose,"D、");

                jsonObject.put("A", A);
                jsonObject.put("B", B);
                jsonObject.put("C", C);
                jsonObject.put("D", D);
            }
            item.setOption(jsonObject);
        });
        subjectVM.setList(list);

        return RestResponse.ok(subjectVM);
    }

    /**
     * 保存答题结果
     */
    @PostMapping(value = "save")
    @ApiOperation("保存答题结果")
    public RestResponse save(@RequestBody ExamMainLogVM examMainLog) {

        examService.save(examMainLog);
        return RestResponse.ok();
    }



}
