package com.alvis.exam.service.impl;


import com.alvis.exam.domain.TSubject;
import com.alvis.exam.repository.TSubjectMapper;
import com.alvis.exam.service.ITSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wufeng
 * @since 2020-06-17
 */
@Service
public class TSubjectServiceImpl extends ServiceImpl<TSubjectMapper, TSubject> implements ITSubjectService {

    @Autowired
    TSubjectMapper subjectMapper;

    @Override
    public void insertData(TSubject tSubject) {
        subjectMapper.insertData(tSubject);
    }
}
