package com.han.gp.vo.student.exam;


import com.han.gp.vo.admin.exam.ExamPaperEditRequest;

public class ExamPaperRead {
    private ExamPaperEditRequest paper;
    private ExamPaperSubmit answer;

    public ExamPaperEditRequest getPaper() {
        return paper;
    }

    public void setPaper(ExamPaperEditRequest paper) {
        this.paper = paper;
    }

    public ExamPaperSubmit getAnswer() {
        return answer;
    }

    public void setAnswer(ExamPaperSubmit answer) {
        this.answer = answer;
    }
}
