package com.alvis.exam.service.impl;

import com.alvis.exam.domain.Dept;
import com.alvis.exam.repository.DeptMapper;
import com.alvis.exam.service.DeptService;
import com.alvis.exam.viewmodel.admin.dept.DeptPageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author sunxiao
 * @Date 2020/7/23
 **/
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int insert(Dept record) {
        return 0;
    }

    @Override
    public int insertByFilter(Dept record) {
        return 0;
    }

    @Override
    public Dept selectById(Integer id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdFilter(Dept record) {
        return 0;
    }

    @Override
    public int updateById(Dept record) {
        return deptMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public PageInfo<Dept> deptPage(DeptPageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id ASC").doSelectPageInfo(() ->
                deptMapper.deptPage(requestVM)
        );
    }
}
