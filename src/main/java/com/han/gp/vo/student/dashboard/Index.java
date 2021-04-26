package com.han.gp.vo.student.dashboard;


import java.util.List;

public class Index {
    private List<PaperInfo> fixedPaper;
    private List<PaperInfoVO> timeLimitPaper;
    private List<PaperInfo> pushPaper;

    public List<PaperInfo> getFixedPaper() {
        return fixedPaper;
    }

    public void setFixedPaper(List<PaperInfo> fixedPaper) {
        this.fixedPaper = fixedPaper;
    }

    public List<PaperInfoVO> getTimeLimitPaper() {
        return timeLimitPaper;
    }

    public void setTimeLimitPaper(List<PaperInfoVO> timeLimitPaper) {
        this.timeLimitPaper = timeLimitPaper;
    }

    public List<PaperInfo> getPushPaper() {
        return pushPaper;
    }

    public void setPushPaper(List<PaperInfo> pushPaper) {
        this.pushPaper = pushPaper;
    }
}
