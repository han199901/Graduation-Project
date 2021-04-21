package com.han.gp.mapper;

import com.han.gp.domain.ExamPaper;
import com.han.gp.vo.admin.exam.ExamPaperPageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamPaperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamPaper record);

    int insertSelective(ExamPaper record);

    ExamPaper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamPaper record);

    int updateByPrimaryKey(ExamPaper record);

    Integer selectAllCount();

    List<ExamPaper> page(ExamPaperPageRequest requestVM);

    List<ExamPaper> taskExamPage(ExamPaperPageRequest model);
}