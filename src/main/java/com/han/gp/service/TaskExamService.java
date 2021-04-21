package com.han.gp.service;

import com.github.pagehelper.PageInfo;
import com.han.gp.domain.TaskExam;
import com.han.gp.domain.User;
import com.han.gp.vo.admin.task.TaskPageRequest;
import com.han.gp.vo.admin.task.TaskRequest;

public interface TaskExamService {
    void edit(TaskRequest model, User currentUser);

    TaskRequest taskExamToVO(Integer id);

    PageInfo<TaskExam> page(TaskPageRequest model);

    TaskExam selectById(Integer id);

    int updateByIdFilter(TaskExam taskExam);
}
