package com.alvis.exam.service.impl;


import com.alvis.exam.domain.TExamPaper;
import com.alvis.exam.repository.TExamPaperMapper;
import com.alvis.exam.service.ITExamPaperService;
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
public class TExamPaperServiceImpl extends ServiceImpl<TExamPaperMapper, TExamPaper> implements ITExamPaperService {

    @Autowired
    TExamPaperMapper examPaperMapper;

    @Override
    public void insertData(TExamPaper tExamPaper) {
        examPaperMapper.insertData(tExamPaper);
    }
}
