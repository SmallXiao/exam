package com.alvis.exam.service;


import com.alvis.exam.domain.TSubject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wufeng
 * @since 2020-06-17
 */
public interface ITSubjectService extends IService<TSubject> {

    void insertData(TSubject tSubject);
}

