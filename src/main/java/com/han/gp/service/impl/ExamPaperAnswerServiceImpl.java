package com.han.gp.service.impl;

import com.han.gp.mapper.ExamPaperAnswerMapper;
import com.han.gp.service.ExamPaperAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamPaperAnswerServiceImpl implements ExamPaperAnswerService {

    @Autowired
    ExamPaperAnswerMapper examPaperAnswerMapper;

    @Override
    public Integer selectAllCount() {
        return examPaperAnswerMapper.selectAllCount();
    }
}
