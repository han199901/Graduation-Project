package com.han.gp.service;

import com.github.pagehelper.PageInfo;
import com.han.gp.domain.ExamPaper;
import com.han.gp.domain.User;
import com.han.gp.vo.admin.exam.ExamPaperEditRequest;
import com.han.gp.vo.admin.exam.ExamPaperPageRequest;

public interface ExamPaperService {
    Integer selectAllCount();
    PageInfo<ExamPaper> page(ExamPaperPageRequest requestVM);

    PageInfo<ExamPaper> taskExamPage(ExamPaperPageRequest model);

    ExamPaperEditRequest examPaperToVO(Integer id);

    ExamPaper savePaperFromVO(ExamPaperEditRequest model, User currentUser);

    int updateByIdFilter(ExamPaper examPaper);

    ExamPaper selectById(Integer id);
}
