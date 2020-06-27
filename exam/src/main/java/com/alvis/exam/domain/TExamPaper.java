package com.alvis.exam.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author wufeng
 * @since 2020-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TExamPaper implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer subjectId;

    private String paperType;

    private String gradeLevel;

    private String score;

    private String questionCount;

    private Integer suggestTime;

    private LocalDateTime limitStartTime;

    private LocalDateTime limitEndTime;

    private Integer frameTextContentId;

    private String createUser;

    private LocalDateTime createTime;

    private String deleted;

    private Integer taskExamId;

    private String flag;

}
