package com.han.gp.vo.admin.task;

import com.han.gp.vo.admin.exam.ExamResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class TaskRequest {

    private Integer id;

    @NotNull
    private Integer gradeLevel;

    @NotNull
    private String title;

    @Size(min = 1, message = "请添加试卷")
    @Valid
    private List<ExamResponse> paperItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(Integer gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ExamResponse> getPaperItems() {
        return paperItems;
    }

    public void setPaperItems(List<ExamResponse> paperItems) {
        this.paperItems = paperItems;
    }
}