package com.han.gp.mapper;

import com.han.gp.domain.ExamPaper;
import com.han.gp.domain.KeyValue;
import com.han.gp.vo.admin.exam.ExamPaperPageRequest;
import com.han.gp.vo.student.dashboard.PaperFilter;
import com.han.gp.vo.student.dashboard.PaperInfo;
import com.han.gp.vo.student.exam.ExamPaperPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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

    List<PaperInfo> indexPaper(PaperFilter paperFilter);

    List<KeyValue> selectCountByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<ExamPaper> studentPage(ExamPaperPage requestVM);

    int updateTaskPaper(@Param("taskId") Integer taskId,@Param("paperIds") List<Integer> paperIds);

    int clearTaskPaper(@Param("paperIds") List<Integer> paperIds);
}