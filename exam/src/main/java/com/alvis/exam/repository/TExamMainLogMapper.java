package com.alvis.exam.repository;

import com.alvis.exam.domain.TExamMainLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TExamMainLogMapper extends BaseMapper<TExamMainLog> {

    int selectCountByUserId(int userId);
}
