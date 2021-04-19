package com.han.gp.service.impl;

import com.han.gp.domain.KeyValue;
import com.han.gp.mapper.ExamPaperQuestionCustomerAnswerMapper;
import com.han.gp.service.ExamPaperQuestionCustomerAnswerService;
import com.han.gp.utility.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamPaperQuestionCustomerAnswerServiceImpl implements ExamPaperQuestionCustomerAnswerService {


    @Autowired
    ExamPaperQuestionCustomerAnswerMapper examPaperQuestionCustomerAnswerMapper;


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
}
