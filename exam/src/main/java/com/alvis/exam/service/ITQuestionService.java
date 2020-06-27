package com.alvis.exam.service;


import com.alvis.exam.domain.TQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

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
}



