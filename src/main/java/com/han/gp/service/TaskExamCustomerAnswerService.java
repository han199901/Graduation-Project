package com.han.gp.service;

import com.han.gp.domain.ExamPaper;
import com.han.gp.domain.ExamPaperAnswer;
import com.han.gp.domain.TaskExamCustomerAnswer;

import java.util.Date;
import java.util.List;

public interface TaskExamCustomerAnswerService {
    TaskExamCustomerAnswer selectByTUid(Integer tid, Integer uid);
    List<TaskExamCustomerAnswer> selectByTUid(List<Integer> taskIds, Integer uid);

    void insertOrUpdate(ExamPaper examPaper, ExamPaperAnswer examPaperAnswer, Date now);

}
