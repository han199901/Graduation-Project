package com.han.gp.controller.teacher;


import com.github.pagehelper.PageInfo;
import com.han.gp.base.BaseApiController;
import com.han.gp.base.RestResponse;
import com.han.gp.domain.TaskExam;
import com.han.gp.service.TaskExamService;
import com.han.gp.utility.DateTimeUtil;
import com.han.gp.utility.PageInfoHelper;
import com.han.gp.vo.admin.task.TaskPageRequest;
import com.han.gp.vo.admin.task.TaskPageResponse;
import com.han.gp.vo.admin.task.TaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("TeacherTaskController")
@RequestMapping(value = "/api/teacher/task")
public class TaskOps extends BaseApiController {

    private final TaskExamService taskExamService;

    @Autowired
    public TaskOps(TaskExamService taskExamService) {
        this.taskExamService = taskExamService;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<TaskPageResponse>> pageList(@RequestBody TaskPageRequest model) {
        PageInfo<TaskExam> pageInfo = taskExamService.page(model);
        PageInfo<TaskPageResponse> page = PageInfoHelper.copyMap(pageInfo, m -> {
            TaskPageResponse vm = modelMapper.map(m, TaskPageResponse.class);
            vm.setCreateTime(DateTimeUtil.dateFormat(m.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResponse edit(@RequestBody @Valid TaskRequest model) {
        taskExamService.edit(model, getCurrentUser());
        TaskRequest vm = taskExamService.taskExamToVO(model.getId());
        return RestResponse.ok(vm);
    }


    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<TaskRequest> select(@PathVariable Integer id) {
        TaskRequest vm = taskExamService.taskExamToVO(id);
        return RestResponse.ok(vm);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public RestResponse delete(@PathVariable Integer id) {
        TaskExam taskExam = taskExamService.selectById(id);
        taskExam.setDeleted(true);
        taskExamService.updateByIdFilter(taskExam);
        return RestResponse.ok();
    }
}
