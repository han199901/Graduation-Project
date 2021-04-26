package com.han.gp.controller.student;

import com.github.pagehelper.PageInfo;
import com.han.gp.base.BaseApiController;
import com.han.gp.base.RestResponse;
import com.han.gp.domain.Class;
import com.han.gp.domain.ExamPaperQuestionCustomerAnswer;
import com.han.gp.domain.QuestionObject;
import com.han.gp.domain.TextContent;
import com.han.gp.service.ClassService;
import com.han.gp.service.ExamPaperQuestionCustomerAnswerService;
import com.han.gp.service.QuestionService;
import com.han.gp.service.TextContentService;
import com.han.gp.utility.DateTimeUtil;
import com.han.gp.utility.HtmlUtil;
import com.han.gp.utility.JsonUtil;
import com.han.gp.utility.PageInfoHelper;
import com.han.gp.vo.admin.question.QuestionEditRequest;
import com.han.gp.vo.student.exam.ExamPaperSubmitItem;
import com.han.gp.vo.student.question.answer.QuestionAnswer;
import com.han.gp.vo.student.question.answer.QuestionPageStudentRequest;
import com.han.gp.vo.student.question.answer.QuestionPageStudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("StudentQuestionAnswerController")
@RequestMapping(value = "/api/student/question/answer")
public class QuestionAnswerController extends BaseApiController {

    private final ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService;
    private final QuestionService questionService;
    private final TextContentService textContentService;
    private final ClassService classService;

    @Autowired
    public QuestionAnswerController(ExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService, QuestionService questionService, TextContentService textContentService, ClassService classService) {
        this.examPaperQuestionCustomerAnswerService = examPaperQuestionCustomerAnswerService;
        this.questionService = questionService;
        this.textContentService = textContentService;
        this.classService = classService;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<QuestionPageStudentResponse>> pageList(@RequestBody QuestionPageStudentRequest model) {
        model.setCreateUser(getCurrentUser().getId());
        PageInfo<ExamPaperQuestionCustomerAnswer> pageInfo = examPaperQuestionCustomerAnswerService.studentPage(model);
        PageInfo<QuestionPageStudentResponse> page = PageInfoHelper.copyMap(pageInfo, q -> {
            Class aClass =  classService.selectById(q.getSubjectId());
            QuestionPageStudentResponse vm = modelMapper.map(q, QuestionPageStudentResponse.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(q.getCreateTime()));
            TextContent textContent = textContentService.selectById(q.getQuestionTextContentId());
            QuestionObject questionObject = JsonUtil.toJsonObject(textContent.getContent(), QuestionObject.class);
            String clearHtml = HtmlUtil.clear(questionObject.getTitleContent());
            vm.setShortTitle(clearHtml);
            vm.setSubjectName(aClass.getName());
            return vm;
        });
        return RestResponse.ok(page);
    }


    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<QuestionAnswer> select(@PathVariable Integer id) {
        QuestionAnswer vm = new QuestionAnswer();
        ExamPaperQuestionCustomerAnswer examPaperQuestionCustomerAnswer = examPaperQuestionCustomerAnswerService.selectById(id);
        ExamPaperSubmitItem questionAnswerVM = examPaperQuestionCustomerAnswerService.examPaperQuestionCustomerAnswerToVO(examPaperQuestionCustomerAnswer);
        QuestionEditRequest questionVM = questionService.getQuestionEditRequest(examPaperQuestionCustomerAnswer.getQuestionId());
        vm.setQuestionVM(questionVM);
        vm.setQuestionAnswerVM(questionAnswerVM);
        return RestResponse.ok(vm);
    }

}
