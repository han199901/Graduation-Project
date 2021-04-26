package com.han.gp.vo.student.dashboard;


import java.util.List;

public class TaskItem {
    private Integer id;
    private String title;
    private List<TaskItemPaper> paperItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TaskItemPaper> getPaperItems() {
        return paperItems;
    }

    public void setPaperItems(List<TaskItemPaper> paperItems) {
        this.paperItems = paperItems;
    }
}
