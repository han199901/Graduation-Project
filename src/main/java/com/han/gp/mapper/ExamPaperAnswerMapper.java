package com.han.gp.mapper;

import com.han.gp.domain.ExamPaperAnswer;
import com.han.gp.vo.admin.paper.ExamPaperAnswerPageRequest;
import com.han.gp.vo.student.exampaper.ExamPaperAnswerPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    List<ExamPaperAnswer> studentPage(ExamPaperAnswerPage requestVM);

    ExamPaperAnswer getByPidUid(@Param("pid") Integer paperId, @Param("uid") Integer uid);
}