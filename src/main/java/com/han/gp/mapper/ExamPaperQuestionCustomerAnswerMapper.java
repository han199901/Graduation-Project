package com.han.gp.mapper;

import com.han.gp.domain.ExamPaperQuestionCustomerAnswer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamPaperQuestionCustomerAnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamPaperQuestionCustomerAnswer record);

    int insertSelective(ExamPaperQuestionCustomerAnswer record);

    ExamPaperQuestionCustomerAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamPaperQuestionCustomerAnswer record);

    int updateByPrimaryKey(ExamPaperQuestionCustomerAnswer record);
}