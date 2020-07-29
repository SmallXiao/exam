package com.alvis.exam.service.impl;

import com.alvis.exam.domain.TExamMainLog;
import com.alvis.exam.repository.TExamMainLogMapper;
import com.alvis.exam.service.TExamMainLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author sunxiao
 * @Date 2020/7/29
 **/
@Service
public class TExamMainLogServiceImpl extends BaseServiceImpl<TExamMainLog> implements TExamMainLogService {


    private final TExamMainLogMapper tExamMainLogMapper;

    @Autowired
    public TExamMainLogServiceImpl(TExamMainLogMapper baseMapper) {
        super(baseMapper);
        this.tExamMainLogMapper = baseMapper;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int insert(TExamMainLog record) {
        return 0;
    }

    @Override
    public int insertByFilter(TExamMainLog record) {
        return 0;
    }

    @Override
    public TExamMainLog selectById(Integer id) {
        return super.selectById(id);
    }

    @Override
    public int updateByIdFilter(TExamMainLog record) {
        return 0;
    }

    @Override
    public int updateById(TExamMainLog record) {
        return super.updateById(record);
    }

    @Override
    public int selectCountByUserId(Integer userId) {
        return tExamMainLogMapper.selectCountByUserId(userId);
    }
}
