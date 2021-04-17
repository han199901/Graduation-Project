package com.han.gp.mapper;

import com.han.gp.domain.TaskExam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskExamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskExam record);

    int insertSelective(TaskExam record);

    TaskExam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskExam record);

    int updateByPrimaryKey(TaskExam record);
}