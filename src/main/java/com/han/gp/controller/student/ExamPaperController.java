package com.han.gp.controller.student;

import com.github.pagehelper.PageInfo;
import com.han.gp.base.BaseApiController;
import com.han.gp.base.RestResponse;
import com.han.gp.domain.ExamPaper;
import com.han.gp.service.ExamPaperAnswerService;
import com.han.gp.service.ExamPaperService;
import com.han.gp.utility.DateTimeUtil;
import com.han.gp.utility.PageInfoHelper;
import com.han.gp.vo.admin.exam.ExamPaperEditRequest;
import com.han.gp.vo.student.exam.ExamPaperPage;
import com.han.gp.vo.student.exam.ExamPaperPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("StudentExamPaperController")
@RequestMapping(value = "/api/student/exam/paper")
public class ExamPaperController extends BaseApiController {

    private final ExamPaperService examPaperService;
    private final ExamPaperAnswerService examPaperAnswerService;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public ExamPaperController(ExamPaperService examPaperService, ExamPaperAnswerService examPaperAnswerService, ApplicationEventPublisher eventPublisher) {
        this.examPaperService = examPaperService;
        this.examPaperAnswerService = examPaperAnswerService;
        this.eventPublisher = eventPublisher;
    }


    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequest> select(@PathVariable Integer id) {
        ExamPaperEditRequest vm = examPaperService.examPaperToVO(id);
        return RestResponse.ok(vm);
    }


    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamPaperPageResponse>> pageList(@RequestBody @Valid ExamPaperPage model) {
        PageInfo<ExamPaper> pageInfo = examPaperService.studentPage(model);
        PageInfo<ExamPaperPageResponse> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamPaperPageResponse vm = modelMapper.map(e, ExamPaperPageResponse.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }
}
