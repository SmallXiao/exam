package com.alvis.exam.repository;

import com.alvis.exam.domain.Dept;
import com.alvis.exam.viewmodel.admin.dept.DeptPageRequestVM;

import java.util.List;

public interface DeptMapper extends BaseMapper<Dept> {


    /**
     * @param requestVM requestVM
     * @return List<User>
     */
    List<Dept> deptPage(DeptPageRequestVM requestVM);

}
