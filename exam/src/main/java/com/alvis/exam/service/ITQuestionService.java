package com.alvis.exam.service;


import com.alvis.exam.domain.TQuestion;
import com.alvis.exam.viewmodel.api.question.QuestionVM;
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
public interface ITQuestionService extends IService<TQuestion> {

    void insertData(TQuestion tQuestion);

    void updateInfoTextContentId(Integer id);

    List<QuestionVM> selectRandomList(List<Integer> subjectIdList, int count);
}



