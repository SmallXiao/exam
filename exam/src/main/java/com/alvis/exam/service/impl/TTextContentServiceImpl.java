package com.alvis.exam.service.impl;

import com.alvis.exam.domain.ContentFlag;
import com.alvis.exam.domain.TQuestion;
import com.alvis.exam.domain.TTextContent;
import com.alvis.exam.repository.TTextContentMapper;
import com.alvis.exam.service.ITTextContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wufeng
 * @since 2020-06-17
 */
@Service
public class TTextContentServiceImpl extends ServiceImpl<TTextContentMapper, TTextContent> implements ITTextContentService {

    @Autowired
    TTextContentMapper textContentMapper;

    @Override
    public void insertData(List<ContentFlag> contentFlags) {
        textContentMapper.insertData(contentFlags);
    }

    @Override
    public void inserttextContentData(TQuestion tQuestion) {
        textContentMapper.inserttextContentData(tQuestion);
    }

    @Override
    public void updateQuestion(List<TQuestion> questionlist) {
        textContentMapper.updateQuestion(questionlist);
    }

    @Override
    public void updatequestionType(List<TTextContent> textContentlist) {
        textContentMapper.updatequestionType(textContentlist);
    }
}
