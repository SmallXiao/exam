package com.alvis.exam.service;

import com.alvis.exam.domain.TExamMainLog;

public interface TExamMainLogService extends BaseService<TExamMainLog> {

    int selectCountByUserId(Integer userId);

}
