package com.han.gp.controller.student;

import com.github.pagehelper.PageInfo;
import com.han.gp.base.BaseApiController;
import com.han.gp.base.RestResponse;
import com.han.gp.domain.Class;
import com.han.gp.domain.ExamPaperAnswer;
import com.han.gp.domain.ExamPaperAnswerInfo;
import com.han.gp.domain.User;
import com.han.gp.domain.enums.ExamPaperAnswerStatusEnum;
import com.han.gp.service.ClassService;
import com.han.gp.service.ExamPaperAnswerService;
import com.han.gp.service.ExamPaperService;
import com.han.gp.service.RelationService;
import com.han.gp.utility.DateTimeUtil;
import com.han.gp.utility.ExamUtil;
import com.han.gp.utility.PageInfoHelper;
import com.han.gp.vo.admin.exam.ExamPaperEditRequest;
import com.han.gp.vo.student.exam.ExamPaperRead;
import com.han.gp.vo.student.exam.ExamPaperSubmit;
import com.han.gp.vo.student.exampaper.ExamPaperAnswerPage;
import com.han.gp.vo.student.exampaper.ExamPaperAnswerPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("StudentExamPaperAnswerController")
@RequestMapping(value = "/api/student/exampaper/answer")
public class ExamPaperAnswerOps extends BaseApiController {

    private final ExamPaperAnswerService examPaperAnswerService;
    private final ExamPaperService examPaperService;

    private final ClassService classService;
    private final RelationService relationService;

    //private final ApplicationEventPublisher eventPublisher;
    @Autowired
    public ExamPaperAnswerOps(ExamPaperAnswerService examPaperAnswerService, ExamPaperService examPaperService, ClassService classService, RelationService relationService) {
        this.examPaperAnswerService = examPaperAnswerService;
        this.examPaperService = examPaperService;
        this.classService = classService;
        this.relationService = relationService;
    }

    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamPaperAnswerPageResponse>> pageList(@RequestBody @Valid ExamPaperAnswerPage model) {
        model.setCreateUser(getCurrentUser().getId());
        PageInfo<ExamPaperAnswer> pageInfo = examPaperAnswerService.studentPage(model);
        PageInfo<ExamPaperAnswerPageResponse> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamPaperAnswerPageResponse vm = modelMapper.map(e, ExamPaperAnswerPageResponse.class);
            Class aClass = classService.selectById(vm.getSubjectId());
            vm.setDoTime(ExamUtil.secondToVM(e.getDoTime()));
            vm.setSystemScore(ExamUtil.scoreToVM(e.getSystemScore()));
            vm.setUserScore(ExamUtil.scoreToVM(e.getUserScore()));
            vm.setPaperScore(ExamUtil.scoreToVM(e.getPaperScore()));
            vm.setSubjectName(aClass.getName());
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }


    @RequestMapping(value = "/answerSubmit", method = RequestMethod.POST)
    public RestResponse answerSubmit(@RequestBody @Valid ExamPaperSubmit examPaperSubmitVM) {
        User user = getCurrentUser();
        ExamPaperAnswerInfo examPaperAnswerInfo = examPaperAnswerService.calculateExamPaperAnswer(examPaperSubmitVM, user);
        if (null == examPaperAnswerInfo) {
            return RestResponse.fail(2, "试卷不能重复做");
        }
        ExamPaperAnswer examPaperAnswer = examPaperAnswerInfo.getExamPaperAnswer();
        Integer userScore = examPaperAnswer.getUserScore();
        String scoreVm = ExamUtil.scoreToVM(userScore);
        String content = user.getUserName() + " 提交试卷：" + examPaperAnswerInfo.getExamPaper().getName()
                + " 得分：" + scoreVm
                + " 耗时：" + ExamUtil.secondToVM(examPaperAnswer.getDoTime());
        examPaperAnswerService.recordExam(examPaperAnswerInfo);
        return RestResponse.ok(scoreVm);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody @Valid ExamPaperSubmit examPaperSubmitVM) {
        boolean notJudge = examPaperSubmitVM.getAnswerItems().stream().anyMatch(i -> i.getDoRight() == null && i.getScore() == null);
        if (notJudge) {
            return RestResponse.fail(2, "有未批改题目");
        }

        ExamPaperAnswer examPaperAnswer = examPaperAnswerService.selectById(examPaperSubmitVM.getId());
        ExamPaperAnswerStatusEnum examPaperAnswerStatusEnum = ExamPaperAnswerStatusEnum.fromCode(examPaperAnswer.getStatus());
        if (examPaperAnswerStatusEnum == ExamPaperAnswerStatusEnum.Complete) {
            return RestResponse.fail(3, "试卷已完成");
        }
        String score = examPaperAnswerService.judge(examPaperSubmitVM);
        User user = getCurrentUser();
        String content = user.getUserName() + " 批改试卷：" + examPaperAnswer.getPaperName() + " 得分：" + score;
        return RestResponse.ok(score);
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.POST)
    public RestResponse<ExamPaperRead> read(@PathVariable Integer id) {
        ExamPaperAnswer examPaperAnswer = examPaperAnswerService.selectById(id);
        ExamPaperRead vm = new ExamPaperRead();
        ExamPaperEditRequest paper = examPaperService.examPaperToVO(examPaperAnswer.getExamPaperId());
        ExamPaperSubmit answer = examPaperAnswerService.examPaperAnswerToVO(examPaperAnswer.getId());
        vm.setPaper(paper);
        vm.setAnswer(answer);
        return RestResponse.ok(vm);
    }


}
