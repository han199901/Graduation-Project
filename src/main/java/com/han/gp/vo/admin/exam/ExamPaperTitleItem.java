package com.han.gp.vo.admin.exam;

import com.han.gp.vo.admin.question.QuestionEditRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class ExamPaperTitleItem {

    @NotBlank(message = "标题内容不能为空")
    private String name;

    @Size(min = 1,message = "请添加题目")
    @Valid
    private List<QuestionEditRequest> questionItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionEditRequest> getQuestionItems() {
        return questionItems;
    }

    public void setQuestionItems(List<QuestionEditRequest> questionItems) {
        this.questionItems = questionItems;
    }
}