package com.alvis.exam.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data

public class Subject implements Serializable {

    private static final long serialVersionUID = 8058095034457106501L;

    private Integer id;

    private String name;

    private Integer level;

    private String levelName;

    private Integer itemOrder;

    private Boolean deleted;
    private String flag;
    private String supplier;

    private Date createTime;
    private Date updateTime;

}
