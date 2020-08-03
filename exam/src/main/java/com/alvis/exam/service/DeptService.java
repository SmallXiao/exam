package com.alvis.exam.service;

import com.alvis.exam.domain.Dept;
import com.alvis.exam.viewmodel.admin.dept.DeptPageRequestVM;
import com.github.pagehelper.PageInfo;

public interface DeptService extends BaseService<Dept> {

    /**
     * @param requestVM requestVM
     * @return PageInfo<User>
     */
    PageInfo<Dept> deptPage(DeptPageRequestVM requestVM);

}
