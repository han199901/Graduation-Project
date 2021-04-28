package com.han.gp.controller.teacher;

import com.han.gp.base.BaseApiController;
import com.han.gp.base.RestResponse;
import com.han.gp.service.ExamPaperAnswerService;
import com.han.gp.service.ExamPaperQuestionCustomerAnswerService;
import com.han.gp.service.ExamPaperService;
import com.han.gp.service.QuestionService;
import com.han.gp.utility.DateTimeUtil;
import com.han.gp.vo.admin.dashboard.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("TeacherDashboardController")
@RequestMapping(value = "/api/teacher/dashboard")
public class DashboardOps extends BaseApiController {

    private final ExamPaperService examPaperService;
    private final QuestionService questionService;
    private final ExamPaperAnswerService examPaperAnswerService;
    private final ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService;

    @Autowired
    public DashboardOps(ExamPaperService examPaperService, QuestionService questionService, ExamPaperAnswerService examPaperAnswerService, ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService) {
        this.examPaperService = examPaperService;
        this.questionService = questionService;
        this.examPaperAnswerService = examPaperAnswerService;
        this.examPaperQuestionCustomerAnswerService = examPaperQuestionCustomerAnswerService;
    }

    @PostMapping("/index")
    public RestResponse<Index> index() {
        Index index = new Index();
        Integer examPaperCount = examPaperService.selectAllCount();
        Integer questionCount = questionService.selectAllCount();
        Integer doExamPaperCount = examPaperAnswerService.selectAllCount();
        Integer doQuestionCount = examPaperQuestionCustomerAnswerService.selectAllCount();
        List<Integer> mothDayDoExamQuestionValue = examPaperQuestionCustomerAnswerService.selectMonthCount();

        index.setDoExamPaperCount(doExamPaperCount);
        index.setQuestionCount(questionCount);
        index.setExamPaperCount(examPaperCount);
        index.setDoQuestionCount(doQuestionCount);
        index.setMothDayDoExamQuestionValue(mothDayDoExamQuestionValue);
        index.setMothDayText(DateTimeUtil.MothDay());

        return RestResponse.ok(index);
    }

}
