package com.alvis.exam.repository;

import com.alvis.exam.domain.Subject;
import com.alvis.exam.viewmodel.admin.education.SubjectPageRequestVM;
import com.alvis.exam.viewmodel.admin.question.QuestionPageRequestVM;
import com.alvis.exam.viewmodel.admin.question.QuestionResponseVM;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubjectMapper  extends BaseMapper<Subject> {
    int deleteByPrimaryKey(Integer id);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);

    List<Subject> getSubjectByLevel(Integer level);

    List<Subject> allSubject();

    List<Subject> page(SubjectPageRequestVM requestVM);

    List<Subject> getSubjectList(String subjectName);

    PageInfo<QuestionResponseVM> subjectpage(QuestionPageRequestVM model);
}
