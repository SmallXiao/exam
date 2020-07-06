package com.alvis.exam.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class TextContent implements Serializable {

    private static final long serialVersionUID = -1279530310964668131L;

    public TextContent( ) {
    }

    public TextContent(String content) {
        this.content = content;
    }

    private Integer id;

    private String content;

    private Date createTime;

    private String question;
    private String choose;
    private String flag;
    private String answer;

}
