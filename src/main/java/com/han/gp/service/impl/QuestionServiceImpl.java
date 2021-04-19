package com.han.gp.service.impl;

import com.han.gp.mapper.QuestionMapper;
import com.han.gp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;


    @Override
    public Integer selectAllCount() {
        return questionMapper.selectAllCount();
    }
}
