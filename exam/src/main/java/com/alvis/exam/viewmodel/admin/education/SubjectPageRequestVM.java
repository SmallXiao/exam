package com.alvis.exam.viewmodel.admin.education;

import com.alvis.exam.base.BasePage;
import lombok.Data;

import java.util.Date;

@Data
public class SubjectPageRequestVM extends BasePage {
    private Integer id;
    private Integer level;
    private String subjectName;
    private String supplier;
    private Date createTime;
    private Date updateTime;
}
