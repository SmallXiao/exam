package com.alvis.exam.service.impl;


import com.alvis.exam.domain.TQuestion;
import com.alvis.exam.repository.TQuestionMapper;
import com.alvis.exam.service.ITQuestionService;
import com.alvis.exam.viewmodel.api.question.QuestionVM;
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
public class TQuestionServiceImpl extends ServiceImpl<TQuestionMapper, TQuestion> implements ITQuestionService {

    @Autowired
    TQuestionMapper questionMapper;

    @Override
    public void insertData(TQuestion tQuestion) {
        questionMapper.insertData(tQuestion);
    }

    @Override
    public void updateInfoTextContentId(Integer id) {
        questionMapper.updateInfoTextContentId(id);
    }

    @Override
    public List<QuestionVM> selectRandomList(List<Integer> subjectIdList, int count) {
        return questionMapper.selectRandomList(subjectIdList, count);
    }
}
