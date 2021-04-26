package com.han.gp.vo.student.question.answer;


import com.han.gp.vo.admin.question.QuestionEditRequest;
import com.han.gp.vo.student.exam.ExamPaperSubmitItem;

public class QuestionAnswer {
    private QuestionEditRequest questionVM;
    private ExamPaperSubmitItem questionAnswerVM;

    public QuestionEditRequest getQuestionVM() {
        return questionVM;
    }

    public void setQuestionVM(QuestionEditRequest questionVM) {
        this.questionVM = questionVM;
    }

    public ExamPaperSubmitItem getQuestionAnswerVM() {
        return questionAnswerVM;
    }

    public void setQuestionAnswerVM(ExamPaperSubmitItem questionAnswerVM) {
        this.questionAnswerVM = questionAnswerVM;
    }
}
