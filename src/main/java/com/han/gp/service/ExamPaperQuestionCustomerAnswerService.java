package com.han.gp.service;

import com.github.pagehelper.PageInfo;
import com.han.gp.domain.ExamPaperAnswerUpdate;
import com.han.gp.domain.ExamPaperQuestionCustomerAnswer;
import com.han.gp.vo.student.exam.ExamPaperSubmitItem;
import com.han.gp.vo.student.question.answer.QuestionPageStudentRequest;

import java.util.List;

public interface ExamPaperQuestionCustomerAnswerService {
    Integer selectAllCount();

    List<Integer> selectMonthCount();

    int updateScore(List<ExamPaperAnswerUpdate> examPaperAnswerUpdates);

    List<ExamPaperQuestionCustomerAnswer> selectListByPaperAnswerId(Integer id);

    ExamPaperSubmitItem examPaperQuestionCustomerAnswerToVO(ExamPaperQuestionCustomerAnswer a);

    ExamPaperQuestionCustomerAnswer selectById(Integer id);

    PageInfo<ExamPaperQuestionCustomerAnswer> studentPage(QuestionPageStudentRequest model);
}
