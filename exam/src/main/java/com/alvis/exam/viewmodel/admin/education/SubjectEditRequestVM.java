package com.alvis.exam.viewmodel.admin.education;

import com.alvis.exam.viewmodel.BaseVM;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SubjectEditRequestVM extends BaseVM {

    private Integer id;

    @NotBlank
    private String name;

    private String supplier;

    @NotNull
    private Integer level;

    @NotBlank
    private String levelName;

    // 套题说明
    private String showContent;

    // 创建人
    private String creator;

    private Date createTime;

}
