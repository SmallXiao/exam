package com.alvis.exam.controller.admin;

import com.alvis.exam.base.BaseApiController;
import com.alvis.exam.base.RestResponse;
import com.alvis.exam.domain.Dept;
import com.alvis.exam.service.DeptService;
import com.alvis.exam.utility.PageInfoHelper;
import com.alvis.exam.viewmodel.admin.dept.DeptPageRequestVM;
import com.alvis.exam.viewmodel.admin.dept.DeptResponseVM;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author sunxiao
 * @Date 2020/8/3
 **/
@RestController("AdminDeptController")
@RequestMapping(value = "/api/admin/dept")
@AllArgsConstructor
public class DeptController extends BaseApiController {

    private final DeptService deptService;

    @RequestMapping(value = "/page/list", method = RequestMethod.POST)
    public RestResponse<PageInfo<DeptResponseVM>> pageList(@RequestBody(required = false) DeptPageRequestVM model) {
        PageInfo<Dept> pageInfo = deptService.deptPage(model);
        PageInfo<DeptResponseVM> page = PageInfoHelper.copyMap(pageInfo, DeptResponseVM::from);
        return RestResponse.ok(page);
    }

}
