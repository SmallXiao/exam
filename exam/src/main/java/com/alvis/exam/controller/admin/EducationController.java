package com.alvis.exam.controller.admin;


import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.Subject;
import com.alvis.exam.domain.TSubject;
import com.alvis.exam.service.ITSubjectService;
import com.alvis.exam.service.SubjectService;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.viewmodel.admin.education.SubjectEditRequestVM;
import com.alvis.exam.viewmodel.admin.education.SubjectPageRequestVM;
import com.alvis.exam.viewmodel.admin.education.SubjectResponseVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("AdminEducationController")
@RequestMapping(value = "/api/admin/education")
@AllArgsConstructor
public class EducationController extends BaseApiController {

    private final SubjectService subjectService;

    @Autowired
    ITSubjectService tsubjectService;

    @RequestMapping(value = "/subject/list", method = RequestMethod.POST)
    public RestResponse<List<Subject>> list() {
        List<Subject> subjects = subjectService.allSubject();
        return RestResponse.ok(subjects);
    }

    @RequestMapping(value = "/subject/insert", method = RequestMethod.POST)
    public RestResponse  insert(@RequestBody TSubject tsubject) {
        tsubjectService.save(tsubject);
        return RestResponse.ok();
    }

    @RequestMapping(value = "/subject/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<SubjectResponseVM>> pageList(@RequestBody SubjectPageRequestVM model) {
        PageInfo<Subject> pageInfo = subjectService.page(model);
        PageInfo<SubjectResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> modelMapper.map(e, SubjectResponseVM.class));
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/subject/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody TSubject subject) {
        tsubjectService.updateById(subject);
        /*Subject subject = modelMapper.map(model, Subject.class);
        if (model.getId() == null) {
            subject.setDeleted(false);
            subjectService.insertByFilter(subject);
        } else {
            subjectService.updateByIdFilter(subject);
        }*/
        return RestResponse.ok();
    }

    @RequestMapping(value = "/subject/select/{id}", method = RequestMethod.POST)
    public RestResponse<SubjectEditRequestVM> select(@PathVariable String id) {
        if ("undefined".equals(id) ) {
            return RestResponse.ok();
        }
        TSubject subject = tsubjectService.getById(id);
        SubjectEditRequestVM vm = modelMapper.map(subject, SubjectEditRequestVM.class);
        return RestResponse.ok(vm);
    }

    @RequestMapping(value = "/subject/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        Subject subject = subjectService.selectById(id);
        subject.setDeleted(true);
        subjectService.updateByIdFilter(subject);
        return RestResponse.ok();
    }
}
