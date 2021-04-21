package com.han.gp.service;

import com.github.pagehelper.PageInfo;
import com.han.gp.domain.Question;
import com.han.gp.vo.admin.question.QuestionEditRequest;
import com.han.gp.vo.admin.question.QuestionPageRequest;

public interface QuestionService {
    Integer selectAllCount();

    QuestionEditRequest getQuestionEditRequest(Question question);

    PageInfo<Question> page(QuestionPageRequest model);

    Question insertFullQuestion(QuestionEditRequest model, Integer id);

    Question updateFullQuestion(QuestionEditRequest model);

    QuestionEditRequest getQuestionEditRequest(Integer id);

    Question selectById(Integer id);

    int updateByIdFilter(Question question);
}
