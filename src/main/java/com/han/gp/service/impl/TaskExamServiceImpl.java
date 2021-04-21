package com.han.gp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.gp.domain.TaskExam;
import com.han.gp.domain.User;
import com.han.gp.mapper.TaskExamMapper;
import com.han.gp.service.TaskExamService;
import com.han.gp.vo.admin.task.TaskPageRequest;
import com.han.gp.vo.admin.task.TaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskExamServiceImpl implements TaskExamService {


    private final TaskExamMapper taskExamMapper;

    @Autowired
    public TaskExamServiceImpl(TaskExamMapper taskExamMapper) {
        this.taskExamMapper = taskExamMapper;
    }

    @Override
    public void edit(TaskRequest model, User currentUser) {

    }

    @Override
    public TaskRequest taskExamToVO(Integer id) {
        return null;
    }

    @Override
    public PageInfo<TaskExam> page(TaskPageRequest model) {
        return PageHelper.startPage(model.getPageIndex(), model.getPageSize(), "id desc").doSelectPageInfo(() ->
                taskExamMapper.page(model)
        );
    }

    @Override
    public TaskExam selectById(Integer id) {
        return taskExamMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdFilter(TaskExam taskExam) {
        return taskExamMapper.updateByPrimaryKeySelective(taskExam);
    }
}
