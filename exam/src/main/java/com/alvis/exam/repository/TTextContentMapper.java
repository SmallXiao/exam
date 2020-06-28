package com.alvis.exam.repository;


import com.alvis.exam.domain.ContentFlag;
import com.alvis.exam.domain.TQuestion;
import com.alvis.exam.domain.TTextContent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wufeng
 * @since 2020-06-17
 */
public interface TTextContentMapper extends BaseMapper<TTextContent> {

    void insertData(List<ContentFlag> contentFlags);

    void inserttextContentData(TQuestion tQuestion);

    void updateQuestion(List<TQuestion> questionlist);

    void updatequestionType(List<TTextContent> textContentlist);
}










