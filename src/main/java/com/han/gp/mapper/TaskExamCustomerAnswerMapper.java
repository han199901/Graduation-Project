package com.han.gp.mapper;

import com.han.gp.domain.TaskExamCustomerAnswer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskExamCustomerAnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskExamCustomerAnswer record);

    int insertSelective(TaskExamCustomerAnswer record);

    TaskExamCustomerAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskExamCustomerAnswer record);

    int updateByPrimaryKey(TaskExamCustomerAnswer record);
}