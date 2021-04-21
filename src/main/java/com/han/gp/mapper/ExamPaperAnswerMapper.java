package com.han.gp.mapper;

import com.han.gp.domain.ExamPaperAnswer;
import com.han.gp.vo.admin.paper.ExamPaperAnswerPageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamPaperAnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamPaperAnswer record);

    int insertSelective(ExamPaperAnswer record);

    ExamPaperAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamPaperAnswer record);

    int updateByPrimaryKey(ExamPaperAnswer record);

    Integer selectAllCount();

    List<ExamPaperAnswer> adminPage(ExamPaperAnswerPageRequest model);
}