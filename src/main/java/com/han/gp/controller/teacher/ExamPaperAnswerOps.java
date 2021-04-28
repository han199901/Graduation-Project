package com.han.gp.controller.teacher;

import com.github.pagehelper.PageInfo;
import com.han.gp.base.BaseApiController;
import com.han.gp.base.RestResponse;
import com.han.gp.domain.Class;
import com.han.gp.domain.ExamPaperAnswer;
import com.han.gp.domain.User;
import com.han.gp.service.ClassService;
import com.han.gp.service.ExamPaperAnswerService;
import com.han.gp.service.UserService;
import com.han.gp.utility.DateTimeUtil;
import com.han.gp.utility.ExamUtil;
import com.han.gp.utility.PageInfoHelper;
import com.han.gp.vo.admin.paper.ExamPaperAnswerPageRequest;
import com.han.gp.vo.student.ExamPaperAnswerPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("TeacherExamPaperAnswerController")
@RequestMapping(value = "/api/teacher/examPaperAnswer")
public class ExamPaperAnswerOps extends BaseApiController {

    private final ExamPaperAnswerService examPaperAnswerService;
    private final UserService userService;
    private final ClassService classService;

    @Autowired
    public ExamPaperAnswerOps(ExamPaperAnswerService examPaperAnswerService, ClassService classService, UserService userService) {
        this.examPaperAnswerService = examPaperAnswerService;
        this.classService = classService;
        this.userService = userService;
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


}