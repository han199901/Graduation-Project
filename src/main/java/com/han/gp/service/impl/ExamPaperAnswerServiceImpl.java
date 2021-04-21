package com.han.gp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.gp.domain.ExamPaperAnswer;
import com.han.gp.mapper.ExamPaperAnswerMapper;
import com.han.gp.service.ExamPaperAnswerService;
import com.han.gp.vo.admin.paper.ExamPaperAnswerPageRequest;
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

    @Override
    public PageInfo<ExamPaperAnswer> adminPage(ExamPaperAnswerPageRequest model) {
        return PageHelper.startPage(model.getPageIndex(), model.getPageSize(), "id desc").doSelectPageInfo(() ->
                examPaperAnswerMapper.adminPage(model));
    }
}
