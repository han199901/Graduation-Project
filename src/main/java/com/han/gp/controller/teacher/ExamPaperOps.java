package com.han.gp.controller.teacher;

import com.github.pagehelper.PageInfo;
import com.han.gp.base.BaseApiController;
import com.han.gp.base.RestResponse;
import com.han.gp.domain.ExamPaper;
import com.han.gp.service.ExamPaperService;
import com.han.gp.utility.DateTimeUtil;
import com.han.gp.utility.PageInfoHelper;
import com.han.gp.vo.admin.exam.ExamPaperEditRequest;
import com.han.gp.vo.admin.exam.ExamPaperPageRequest;
import com.han.gp.vo.admin.exam.ExamResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("TeacherExamPaperController")
@RequestMapping(value = "/api/teacher/exam/paper")
public class ExamPaperOps extends BaseApiController {
    private final ExamPaperService examPaperService;

    @Autowired
    public ExamPaperOps(ExamPaperService examPaperService) {
        this.examPaperService = examPaperService;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamResponse>> pageList(@RequestBody ExamPaperPageRequest model) {
        PageInfo<ExamPaper> pageInfo = examPaperService.page(model);
        PageInfo<ExamResponse> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamResponse vm = modelMapper.map(e, ExamResponse.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }

    @RequestMapping(value = "/taskExamPage", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamResponse>> taskExamPageList(@RequestBody ExamPaperPageRequest model) {
        PageInfo<ExamPaper> pageInfo = examPaperService.taskExamPage(model);
        PageInfo<ExamResponse> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamResponse vm = modelMapper.map(e, ExamResponse.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }



    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequest> edit(@RequestBody @Valid ExamPaperEditRequest model) {
        ExamPaper examPaper = examPaperService.savePaperFromVO(model, getCurrentUser());
        ExamPaperEditRequest newVM = examPaperService.examPaperToVO(examPaper.getId());
        return RestResponse.ok(newVM);
    }

    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequest> select(@PathVariable Integer id) {
        ExamPaperEditRequest vm = examPaperService.examPaperToVO(id);
        return RestResponse.ok(vm);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        ExamPaper examPaper = examPaperService.selectById(id);
        examPaper.setDeleted(true);
        examPaperService.updateByIdFilter(examPaper);
        return RestResponse.ok();
    }
}
