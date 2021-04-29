package com.han.gp.service;

import com.github.pagehelper.PageInfo;
import com.han.gp.domain.ExamPaperAnswer;
import com.han.gp.domain.ExamPaperAnswerInfo;
import com.han.gp.domain.User;
import com.han.gp.vo.admin.paper.ExamPaperAnswerPageRequest;
import com.han.gp.vo.student.exam.ExamPaperSubmit;
import com.han.gp.vo.student.exampaper.ExamPaperAnswerPage;

public interface ExamPaperAnswerService {
    Integer selectAllCount();

    PageInfo<ExamPaperAnswer> adminPage(ExamPaperAnswerPageRequest model);

    /**
     * 学生考试记录分页
     *
     * @param requestVM 过滤条件
     * @return PageInfo<ExamPaperAnswer>
     */
    PageInfo<ExamPaperAnswer> studentPage(ExamPaperAnswerPage requestVM);

    PageInfo<ExamPaperAnswer> studentPageByTeacher(ExamPaperAnswerPage requestVM);

    ExamPaperAnswerInfo calculateExamPaperAnswer(ExamPaperSubmit examPaperSubmitVM, User user);

    String judge(ExamPaperSubmit examPaperSubmitVM);

    ExamPaperAnswer selectById(Integer id);

    ExamPaperSubmit examPaperAnswerToVO(Integer id);

    void recordExam(ExamPaperAnswerInfo examPaperAnswerInfo);
}
