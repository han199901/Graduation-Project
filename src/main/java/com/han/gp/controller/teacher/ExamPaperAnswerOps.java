package com.han.gp.controller.teacher;

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
import com.han.gp.service.UserService;
import com.han.gp.utility.DateTimeUtil;
import com.han.gp.utility.ExamUtil;
import com.han.gp.utility.PageInfoHelper;
import com.han.gp.vo.admin.exam.ExamPaperEditRequest;
import com.han.gp.vo.admin.paper.ExamPaperAnswerPageRequest;
import com.han.gp.vo.student.ExamPaperAnswerPageResponse;
import com.han.gp.vo.student.exam.ExamPaperRead;
import com.han.gp.vo.student.exam.ExamPaperSubmit;
import com.han.gp.vo.student.exampaper.ExamPaperAnswerPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("TeacherExamPaperAnswerController")
@RequestMapping(value = "/api/teacher/examPaperAnswer")
public class ExamPaperAnswerOps extends BaseApiController {

    private final ExamPaperAnswerService examPaperAnswerService;
    private final ExamPaperService examPaperService;
    private final UserService userService;
    private final ClassService classService;

    @Autowired
    public ExamPaperAnswerOps(ExamPaperAnswerService examPaperAnswerService, ExamPaperService examPaperService, UserService userService, ClassService classService) {
        this.examPaperAnswerService = examPaperAnswerService;
        this.examPaperService = examPaperService;
        this.userService = userService;
        this.classService = classService;
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


    @PostMapping(value = "/page")
    public RestResponse<PageInfo<ExamPaperAnswerPageResponse>> pageJudgeList(@RequestBody ExamPaperAnswerPageRequest model) {
        PageInfo<ExamPaperAnswer> pageInfo = examPaperAnswerService.adminPage(model);
        PageInfo<ExamPaperAnswerPageResponse> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamPaperAnswerPageResponse vm = modelMapper.map(e, ExamPaperAnswerPageResponse.class);
            Class aClass = classService.selectById(vm.getSubjectId());
            vm.setDoTime(ExamUtil.secondToVM(e.getDoTime()));
            vm.setSystemScore(ExamUtil.scoreToVM(e.getSystemScore()));
            vm.setUserScore(ExamUtil.scoreToVM(e.getUserScore()));
            vm.setPaperScore(ExamUtil.scoreToVM(e.getPaperScore()));
            vm.setSubjectName(aClass.getName());
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            User user = userService.selectById(e.getCreateUser());
            vm.setUserName(user.getUserName());
            return vm;
        });
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public RestResponse<PageInfo<com.han.gp.vo.student.exampaper.ExamPaperAnswerPageResponse>> pageList(@RequestBody @Valid ExamPaperAnswerPage model) {
        model.setCreateUser(getCurrentUser().getId());
        PageInfo<ExamPaperAnswer> pageInfo = examPaperAnswerService.studentPageByTeacher(model);
        PageInfo<com.han.gp.vo.student.exampaper.ExamPaperAnswerPageResponse> page = PageInfoHelper.copyMap(pageInfo, e -> {
            com.han.gp.vo.student.exampaper.ExamPaperAnswerPageResponse vm = modelMapper.map(e, com.han.gp.vo.student.exampaper.ExamPaperAnswerPageResponse.class);
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