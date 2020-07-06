package com.alvis.exam.repository;


import com.alvis.exam.domain.TQuestion;
import com.alvis.exam.viewmodel.api.question.QuestionVM;
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
public interface TQuestionMapper extends BaseMapper<TQuestion> {

    void insertData(TQuestion tQuestion);

    void updateInfoTextContentId(Integer id);

    List<QuestionVM> selectRandomList(List<Integer> subjectIdList, int count);
}


