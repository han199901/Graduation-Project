package com.han.gp.service;

import com.github.pagehelper.PageInfo;
import com.han.gp.domain.ExamPaperAnswer;
import com.han.gp.vo.admin.paper.ExamPaperAnswerPageRequest;

public interface ExamPaperAnswerService {
    Integer selectAllCount();

    PageInfo<ExamPaperAnswer> adminPage(ExamPaperAnswerPageRequest model);
}
