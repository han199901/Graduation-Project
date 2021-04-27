package com.han.gp.mapper;

import com.han.gp.domain.ExamPaperQuestionCustomerAnswer;
import com.han.gp.domain.KeyValue;
import com.han.gp.vo.student.question.answer.QuestionPageStudentRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface ExamPaperQuestionCustomerAnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamPaperQuestionCustomerAnswer record);

    int insertSelective(ExamPaperQuestionCustomerAnswer record);

    ExamPaperQuestionCustomerAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamPaperQuestionCustomerAnswer record);

    int updateByPrimaryKey(ExamPaperQuestionCustomerAnswer record);

    Integer selectAllCount();

    List<KeyValue> selectCountByDate(Date startTime, Date endTime);

    List<ExamPaperQuestionCustomerAnswer> studentPage(QuestionPageStudentRequest model);

    int insertList(List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers);

    List<ExamPaperQuestionCustomerAnswer> selectListByPaperAnswerId(Integer id);
}