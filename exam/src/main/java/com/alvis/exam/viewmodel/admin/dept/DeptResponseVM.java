package com.alvis.exam.viewmodel.admin.dept;

import com.alvis.exam.domain.Dept;
import com.alvis.exam.utility.DateTimeUtil;
import com.alvis.exam.viewmodel.BaseVM;
import lombok.Data;


/**
 * @Author sunxiao
 * @Date 2020/8/3
 **/
@Data
public class DeptResponseVM extends BaseVM {

    private Integer id;

    private String name;

    private String createTime;

    private Integer createUser;

    private String updateTime;

    private Integer updateUser;



    public static DeptResponseVM from(Dept dept) {
        DeptResponseVM vm = modelMapper.map(dept, DeptResponseVM.class);

        vm.setCreateTime(DateTimeUtil.dateFormat(dept.getCreateTime()));
        vm.setUpdateTime(DateTimeUtil.dateFormat(dept.getUpdateTime()));
        return vm;
    }

}
