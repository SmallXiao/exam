package com.alvis.exam.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Question implements Serializable {

    private static final long serialVersionUID = 8826266720383164363L;

    private Integer id;

    private Integer questionType;

    private Integer subjectId;

    private Integer paperId;

    private Integer score;

    private Integer gradeLevel;

    private Integer difficult;

    private String correct;

    private Integer infoTextContentId;

    private Integer createUser;

    private Integer status;

    private Date createTime;

    private Boolean deleted;

    private String flag;

    private String name;


}
