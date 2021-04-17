package com.han.gp.mapper;

import com.han.gp.domain.ExamPaperAnswer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamPaperAnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamPaperAnswer record);

    int insertSelective(ExamPaperAnswer record);

    ExamPaperAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamPaperAnswer record);

    int updateByPrimaryKey(ExamPaperAnswer record);
}