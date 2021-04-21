package com.han.gp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.gp.domain.Question;
import com.han.gp.domain.QuestionItemObject;
import com.han.gp.domain.QuestionObject;
import com.han.gp.domain.TextContent;
import com.han.gp.domain.enums.QuestionStatusEnum;
import com.han.gp.domain.enums.QuestionTypeEnum;
import com.han.gp.mapper.QuestionMapper;
import com.han.gp.service.QuestionService;
import com.han.gp.service.TextContentService;
import com.han.gp.utility.ExamUtil;
import com.han.gp.utility.JsonUtil;
import com.han.gp.utility.ModelMapperSingle;
import com.han.gp.vo.admin.question.QuestionEditItem;
import com.han.gp.vo.admin.question.QuestionEditRequest;
import com.han.gp.vo.admin.question.QuestionPageRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();
    private final QuestionMapper questionMapper;
    private final TextContentService textContentService;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper, TextContentService textContentService) {
        this.questionMapper = questionMapper;
        this.textContentService = textContentService;
    }

    @Override
    public Integer selectAllCount() {
        return questionMapper.selectAllCount();
    }

    @Override
    public QuestionEditRequest getQuestionEditRequest(Question question) {
        //题目映射
        TextContent questionInfoTextContent = textContentService.selectById(question.getInfoTextContentId());
        QuestionObject questionObject = JsonUtil.toJsonObject(questionInfoTextContent.getContent(), QuestionObject.class);
        QuestionEditRequest questionEditRequestVM = modelMapper.map(question, QuestionEditRequest.class);
        questionEditRequestVM.setTitle(questionObject.getTitleContent());

        //答案
        QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.fromCode(question.getQuestionType());
        switch (questionTypeEnum) {
            case SingleChoice:
            case TrueFalse:
                questionEditRequestVM.setCorrect(question.getCorrect());
                break;
            case MultipleChoice:
                questionEditRequestVM.setCorrectArray(ExamUtil.contentToArray(question.getCorrect()));
                break;
            case GapFilling:
                List<String> correctContent = questionObject.getQuestionItemObjects().stream().map(d -> d.getContent()).collect(Collectors.toList());
                questionEditRequestVM.setCorrectArray(correctContent);
                break;
            case ShortAnswer:
                questionEditRequestVM.setCorrect(questionObject.getCorrect());
                break;
            default:
                break;
        }
        questionEditRequestVM.setScore(ExamUtil.scoreToVM(question.getScore()));
        questionEditRequestVM.setAnalyze(questionObject.getAnalyze());


        //题目项映射
        List<QuestionEditItem> editItems = questionObject.getQuestionItemObjects().stream().map(o -> {
            QuestionEditItem questionEditItemVM = modelMapper.map(o, QuestionEditItem.class);
            if (o.getScore() != null) {
                questionEditItemVM.setScore(ExamUtil.scoreToVM(o.getScore()));
            }
            return questionEditItemVM;
        }).collect(Collectors.toList());
        questionEditRequestVM.setItems(editItems);
        return questionEditRequestVM;
    }

    @Override
    public PageInfo<Question> page(QuestionPageRequest model) {
        return PageHelper.startPage(model.getPageIndex(), model.getPageSize(), "id desc").doSelectPageInfo(() ->
                questionMapper.page(model)
        );
    }

    @Override
    @Transactional
    public Question insertFullQuestion(QuestionEditRequest model, Integer id) {
        Date now = new Date();
        Integer gradeLevel = model.getSubjectId();

        //题干、解析、选项等 插入
        TextContent infoTextContent = new TextContent();
        infoTextContent.setCreateTime(now);
        setQuestionInfoFromVM(infoTextContent, model);
        textContentService.insertByFilter(infoTextContent);

        Question question = new Question();
        question.setSubjectId(model.getSubjectId());
        question.setGradeLevel(gradeLevel);
        question.setCreateTime(now);
        question.setQuestionType(model.getQuestionType());
        question.setStatus(QuestionStatusEnum.OK.getCode());
        question.setCorrectFromVO(model.getCorrect(), model.getCorrectArray());
        question.setScore(ExamUtil.scoreFromVM(model.getScore()));
        question.setDifficult(model.getDifficult());
        question.setInfoTextContentId(infoTextContent.getId());
        question.setCreateUser(id);
        question.setDeleted(false);
        questionMapper.insertSelective(question);
        return question;
    }

    @Override
    @Transactional
    public Question updateFullQuestion(QuestionEditRequest model) {
        Integer gradeLevel = model.getSubjectId();
        Question question = questionMapper.selectByPrimaryKey(model.getId());
        question.setSubjectId(model.getSubjectId());
        question.setGradeLevel(gradeLevel);
        question.setScore(ExamUtil.scoreFromVM(model.getScore()));
        question.setDifficult(model.getDifficult());
        question.setCorrectFromVO(model.getCorrect(), model.getCorrectArray());
        questionMapper.updateByPrimaryKeySelective(question);

        //题干、解析、选项等 更新
        TextContent infoTextContent = textContentService.selectById(question.getInfoTextContentId());
        setQuestionInfoFromVM(infoTextContent, model);
        textContentService.updateByIdFilter(infoTextContent);

        return question;
    }

    @Override
    public QuestionEditRequest getQuestionEditRequest(Integer id) {
        //题目映射
        Question question = questionMapper.selectByPrimaryKey(id);
        return getQuestionEditRequest(question);
    }

    @Override
    public Question selectById(Integer id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdFilter(Question question) {
        return questionMapper.updateByPrimaryKeySelective(question);
    }

    public void setQuestionInfoFromVM(TextContent infoTextContent, QuestionEditRequest model) {
        List<QuestionItemObject> itemObjects = model.getItems().stream().map(i ->
                {
                    QuestionItemObject item = new QuestionItemObject();
                    item.setPrefix(i.getPrefix());
                    item.setContent(i.getContent());
                    item.setScore(ExamUtil.scoreFromVM(i.getScore()));
                    return item;
                }
        ).collect(Collectors.toList());
        QuestionObject questionObject = new QuestionObject();
        questionObject.setQuestionItemObjects(itemObjects);
        questionObject.setAnalyze(model.getAnalyze());
        questionObject.setTitleContent(model.getTitle());
        questionObject.setCorrect(model.getCorrect());
        infoTextContent.setContent(JsonUtil.toJsonStr(questionObject));
    }
}
