package com.alvis.exam.service;


import com.alvis.exam.domain.ContentFlag;
import com.alvis.exam.domain.TQuestion;
import com.alvis.exam.domain.TTextContent;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wufeng
 * @since 2020-06-17
 */
public interface ITTextContentService extends IService<TTextContent> {

    void insertData(List<ContentFlag> contentFlags);

    void inserttextContentData(TQuestion tQuestion);

    void updateQuestion(List<TQuestion> questionlist);

    void updatequestionType(List<TTextContent> textContentlist);
}




