package com.alvis.exam.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author sunxiao
 * @Date 2020/7/23
 **/
@Getter
@Setter
public class Dept implements Serializable {

    private Integer id;

    private String name;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;
}
