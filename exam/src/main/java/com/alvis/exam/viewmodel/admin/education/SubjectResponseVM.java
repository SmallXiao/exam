package com.alvis.exam.viewmodel.admin.education;

import com.alvis.exam.viewmodel.BaseVM;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SubjectResponseVM extends BaseVM {
    private Integer id;

    private String name;
    private String supplier;

    private Integer level;

    private String levelName;
    private String subjectName;
    private Integer questionCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
