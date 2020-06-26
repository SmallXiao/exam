package com.alvis.exam.controller.api;

import com.alvis.exam.base.RestResponse;
import com.alvis.exam.service.ExamService;
import com.alvis.exam.viewmodel.api.exam.ExamMainLogVM;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 考试设置相关
 * @Author sunxiao
 * @Date 2020/6/15
 **/
@RestController("ExamController")
@RequestMapping(value = "/api/exam")
@AllArgsConstructor
public class ExamController {

    @Autowired
    private ExamService examService;


    /**
     * 随机获取套题
     * @return
     */
    @GetMapping(value = "/randomlist/{count}")
    public RestResponse randomList(@PathVariable(required = false) Integer count) {
        if (count == null) {
            count = 10;
        }

//        examService


        return RestResponse.ok(null);
    }

    /**
     * 保存答题结果
     */
    @PostMapping(value = "save")
    public RestResponse save(@RequestBody ExamMainLogVM examMainLog) {

        examService.save(examMainLog);
        return RestResponse.ok();
    }



}
