package com.han.gp.service.impl;

import com.han.gp.mapper.ExamPaperMapper;
import com.han.gp.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamPaperServiceImpl implements ExamPaperService {


    @Autowired
    ExamPaperMapper examPaperMapper;

    @Override
    public Integer selectAllCount() {
        return examPaperMapper.selectAllCount();
    }
}
