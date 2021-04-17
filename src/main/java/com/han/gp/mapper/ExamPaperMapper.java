package com.han.gp.mapper;

import com.han.gp.domain.ExamPaper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamPaperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamPaper record);

    int insertSelective(ExamPaper record);

    ExamPaper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamPaper record);

    int updateByPrimaryKey(ExamPaper record);
}