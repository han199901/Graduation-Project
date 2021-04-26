package com.han.gp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.gp.domain.*;
import com.han.gp.domain.enums.ActionEnum;
import com.han.gp.mapper.ExamPaperMapper;
import com.han.gp.mapper.TaskExamMapper;
import com.han.gp.service.TaskExamService;
import com.han.gp.service.TextContentService;
import com.han.gp.utility.DateTimeUtil;
import com.han.gp.utility.JsonUtil;
import com.han.gp.utility.ModelMapperSingle;
import com.han.gp.vo.admin.exam.ExamResponse;
import com.han.gp.vo.admin.task.TaskPageRequest;
import com.han.gp.vo.admin.task.TaskRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskExamServiceImpl implements TaskExamService {

    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

    private final TaskExamMapper taskExamMapper;
    private final TextContentService textContentService;
    private final ExamPaperMapper examPaperMapper;

    @Autowired
    public TaskExamServiceImpl(TaskExamMapper taskExamMapper, TextContentService textContentService, ExamPaperMapper examPaperMapper) {
        this.taskExamMapper = taskExamMapper;
        this.textContentService = textContentService;
        this.examPaperMapper = examPaperMapper;
    }


    @Override
    @Transactional
    public void edit(TaskRequest model, User user) {
        ActionEnum actionEnum = (model.getId() == null) ? ActionEnum.ADD : ActionEnum.UPDATE;
        TaskExam taskExam = null;
        if (actionEnum == ActionEnum.ADD) {
            Date now = new Date();
            taskExam = modelMapper.map(model, TaskExam.class);
            taskExam.setCreateUser(user.getId());
            taskExam.setCreateUserName(user.getUserName());
            taskExam.setCreateTime(now);
            taskExam.setDeleted(false);

            //保存任务结构
            TextContent textContent = textContentService.jsonConvertInsert(model.getPaperItems(), now, p -> {
                TaskItemObject taskItemObject = new TaskItemObject();
                taskItemObject.setExamPaperId(p.getId());
                taskItemObject.setExamPaperName(p.getName());
                return taskItemObject;
            });
            textContentService.insertByFilter(textContent);
            taskExam.setFrameTextContentId(textContent.getId());
            taskExamMapper.insertSelective(taskExam);

        } else {
            taskExam = taskExamMapper.selectByPrimaryKey(model.getId());
            modelMapper.map(model, taskExam);

            TextContent textContent = textContentService.selectById(taskExam.getFrameTextContentId());
            //清空试卷任务的试卷Id，后面会统一设置
            List<Integer> paperIds = JsonUtil.toJsonListObject(textContent.getContent(), TaskItemObject.class)
                    .stream()
                    .map(d -> d.getExamPaperId())
                    .collect(Collectors.toList());
            examPaperMapper.clearTaskPaper(paperIds);

            //更新任务结构
            textContentService.jsonConvertUpdate(textContent, model.getPaperItems(), p -> {
                TaskItemObject taskItemObject = new TaskItemObject();
                taskItemObject.setExamPaperId(p.getId());
                taskItemObject.setExamPaperName(p.getName());
                return taskItemObject;
            });
            textContentService.updateByIdFilter(textContent);
            taskExamMapper.updateByPrimaryKeySelective(taskExam);
        }

        //更新试卷的taskId
        List<Integer> paperIds = model.getPaperItems().stream().map(d -> d.getId()).collect(Collectors.toList());
        examPaperMapper.updateTaskPaper(taskExam.getId(), paperIds);
        model.setId(taskExam.getId());
    }

    @Override
    public TaskRequest taskExamToVO(Integer id) {
        TaskExam taskExam = taskExamMapper.selectByPrimaryKey(id);
        TaskRequest vm = modelMapper.map(taskExam, TaskRequest.class);
        TextContent textContent = textContentService.selectById(taskExam.getFrameTextContentId());
        List<ExamResponse> examResponseVMS = JsonUtil.toJsonListObject(textContent.getContent(), TaskItemObject.class).stream().map(tk -> {
            ExamPaper examPaper = examPaperMapper.selectByPrimaryKey(tk.getExamPaperId());
            ExamResponse examResponseVM = modelMapper.map(examPaper, ExamResponse.class);
            examResponseVM.setCreateTime(DateTimeUtil.dateFormat(examPaper.getCreateTime()));
            return examResponseVM;
        }).collect(Collectors.toList());
        vm.setPaperItems(examResponseVMS);
        return vm;
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

    @Override
    public List<TaskExam> getByGradeLevel(Integer gradeLevel) {
        return taskExamMapper.getByGradeLevel(gradeLevel);
    }
}
