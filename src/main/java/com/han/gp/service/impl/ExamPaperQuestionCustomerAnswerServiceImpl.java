package com.han.gp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.gp.domain.ExamPaperAnswerUpdate;
import com.han.gp.domain.ExamPaperQuestionCustomerAnswer;
import com.han.gp.domain.KeyValue;
import com.han.gp.domain.TextContent;
import com.han.gp.domain.enums.QuestionTypeEnum;
import com.han.gp.mapper.ExamPaperQuestionCustomerAnswerMapper;
import com.han.gp.service.ExamPaperQuestionCustomerAnswerService;
import com.han.gp.service.TextContentService;
import com.han.gp.utility.DateTimeUtil;
import com.han.gp.utility.ExamUtil;
import com.han.gp.utility.JsonUtil;
import com.han.gp.vo.student.exam.ExamPaperSubmitItem;
import com.han.gp.vo.student.question.answer.QuestionPageStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamPaperQuestionCustomerAnswerServiceImpl implements ExamPaperQuestionCustomerAnswerService {


    private final ExamPaperQuestionCustomerAnswerMapper examPaperQuestionCustomerAnswerMapper;
    private final TextContentService textContentService;

    @Autowired
    public ExamPaperQuestionCustomerAnswerServiceImpl(ExamPaperQuestionCustomerAnswerMapper examPaperQuestionCustomerAnswerMapper, TextContentService textContentService) {
        this.examPaperQuestionCustomerAnswerMapper = examPaperQuestionCustomerAnswerMapper;
        this.textContentService = textContentService;
    }

    private void setSpecialToVO(ExamPaperSubmitItem examPaperSubmitItemVM, ExamPaperQuestionCustomerAnswer examPaperQuestionCustomerAnswer) {
        QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.fromCode(examPaperQuestionCustomerAnswer.getQuestionType());
        switch (questionTypeEnum) {
            case MultipleChoice:
                examPaperSubmitItemVM.setContent(examPaperQuestionCustomerAnswer.getAnswer());
                examPaperSubmitItemVM.setContentArray(ExamUtil.contentToArray(examPaperQuestionCustomerAnswer.getAnswer()));
                break;
            case GapFilling:
                TextContent textContent = textContentService.selectById(examPaperQuestionCustomerAnswer.getTextContentId());
                List<String> correctAnswer = JsonUtil.toJsonListObject(textContent.getContent(), String.class);
                examPaperSubmitItemVM.setContentArray(correctAnswer);
                break;
            default:
                if (QuestionTypeEnum.needSaveTextContent(examPaperQuestionCustomerAnswer.getQuestionType())) {
                    TextContent content = textContentService.selectById(examPaperQuestionCustomerAnswer.getTextContentId());
                    examPaperSubmitItemVM.setContent(content.getContent());
                } else {
                    examPaperSubmitItemVM.setContent(examPaperQuestionCustomerAnswer.getAnswer());
                }
                break;
        }
    }


    @Override
    public Integer selectAllCount() {
        return examPaperQuestionCustomerAnswerMapper.selectAllCount();
    }

    @Override
    public List<Integer> selectMonthCount() {
        Date startTime = DateTimeUtil.getMonthStartDay();
        Date endTime = DateTimeUtil.getMonthEndDay();
        List<KeyValue> mouthCount = examPaperQuestionCustomerAnswerMapper.selectCountByDate(startTime, endTime);
        List<String> mothStartToNowFormat = DateTimeUtil.MothStartToNowFormat();
        return mothStartToNowFormat.stream().map(md -> {
            KeyValue keyValue = mouthCount.stream().filter(kv -> kv.getName().equals(md)).findAny().orElse(null);
            return null == keyValue ? 0 : keyValue.getValue();
        }).collect(Collectors.toList());
    }

    @Override
    public int updateScore(List<ExamPaperAnswerUpdate> examPaperAnswerUpdates) {
        return 0;
    }

    @Override
    public List<ExamPaperQuestionCustomerAnswer> selectListByPaperAnswerId(Integer id) {
        return examPaperQuestionCustomerAnswerMapper.selectListByPaperAnswerId(id);
    }

    @Override
    public ExamPaperSubmitItem examPaperQuestionCustomerAnswerToVO(ExamPaperQuestionCustomerAnswer a) {
        ExamPaperSubmitItem examPaperSubmitItemVM = new ExamPaperSubmitItem();
        examPaperSubmitItemVM.setId(a.getId());
        examPaperSubmitItemVM.setQuestionId(a.getQuestionId());
        examPaperSubmitItemVM.setDoRight(a.getDoRight());
        examPaperSubmitItemVM.setItemOrder(a.getItemOrder());
        examPaperSubmitItemVM.setQuestionScore(ExamUtil.scoreToVM(a.getQuestionScore()));
        examPaperSubmitItemVM.setScore(ExamUtil.scoreToVM(a.getCustomerScore()));
        setSpecialToVO(examPaperSubmitItemVM, a);
        return examPaperSubmitItemVM;
    }

    @Override
    public ExamPaperQuestionCustomerAnswer selectById(Integer id) {
        return examPaperQuestionCustomerAnswerMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<ExamPaperQuestionCustomerAnswer> studentPage(QuestionPageStudentRequest model) {
        return PageHelper.startPage(model.getPageIndex(), model.getPageSize(), "id desc").doSelectPageInfo(() ->
                examPaperQuestionCustomerAnswerMapper.studentPage(model)
        );
    }

    @Override
    public void insertList(List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers) {
        examPaperQuestionCustomerAnswerMapper.insertList(examPaperQuestionCustomerAnswers);
    }
}
