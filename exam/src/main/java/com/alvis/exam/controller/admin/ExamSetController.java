package com.alvis.exam.controller.admin;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.ExamSet;
import com.alvis.exam.domain.User;
import com.alvis.exam.service.ExamSetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * 考试设置相关
 * @Author sunxiao
 * @Date 2020/6/15
 **/
@RestController("AdminExamSetController")
@RequestMapping(value = "/api/admin/examSet")
@AllArgsConstructor
public class ExamSetController extends BaseApiController {

    private final ExamSetService examSetService;


    /**
     * 考题设置
     */
    @PostMapping(value = "/select/{id}")
    public RestResponse select(@PathVariable(required = false) Integer id) {
        if (id == null) {
            id = 1;
        }
        ExamSet examSet = examSetService.select(id);

        return RestResponse.ok(examSet);
    }


    /**
     * 考题设置
     */
    @PostMapping(value = "/edit")
    public RestResponse edit(@RequestBody ExamSet model) {
        User user = getCurrentUser();
        model.setUpdateUser(user.getId());
        model.setUpdateTime(new Date());
        examSetService.edit(model);

        return RestResponse.ok();
    }


}
