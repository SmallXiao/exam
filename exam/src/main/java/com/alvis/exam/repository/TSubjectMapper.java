package com.alvis.exam.repository;

import com.alvis.exam.domain.TSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wufeng
 * @since 2020-06-17
 */
public interface TSubjectMapper extends BaseMapper<TSubject> {

    void insertData(TSubject tSubject);
}

