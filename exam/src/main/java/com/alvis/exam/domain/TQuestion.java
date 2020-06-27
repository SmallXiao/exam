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
public class TQuestion implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String questionType;

    /**
     * 学科id
     */
    private Integer subjectId;

    /**
     * 试卷id
     */
    private Integer paperId;

    private String score;

    private String gradeLevel;

    private String difficult;

    private String correct;

    private Integer infoTextContentId;

    private String createUser;

    private String status;

    private LocalDateTime createTime;

    private String deleted;

    private String flag;

    private String name;

}
