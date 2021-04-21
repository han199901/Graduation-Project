package com.han.gp.vo.admin.question;



import javax.validation.constraints.NotBlank;


public class QuestionEditItem {
    @NotBlank
    private String prefix;
    @NotBlank
    private String content;

    private String score;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
