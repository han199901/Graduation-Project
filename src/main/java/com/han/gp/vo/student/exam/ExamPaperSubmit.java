package com.han.gp.vo.student.exam;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


public class ExamPaperSubmit {

    @NotNull
    private Integer id;

    @NotNull
    private Integer doTime;

    private String score;

    @NotNull
    @Valid
    private List<ExamPaperSubmitItem> answerItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoTime() {
        return doTime;
    }

    public void setDoTime(Integer doTime) {
        this.doTime = doTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<ExamPaperSubmitItem> getAnswerItems() {
        return answerItems;
    }

    public void setAnswerItems(List<ExamPaperSubmitItem> answerItems) {
        this.answerItems = answerItems;
    }
}
