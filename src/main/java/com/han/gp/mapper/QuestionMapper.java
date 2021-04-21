package com.han.gp.mapper;

import com.han.gp.domain.Question;
import com.han.gp.vo.admin.question.QuestionPageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKeyWithBLOBs(Question record);

    int updateByPrimaryKey(Question record);

    Integer selectAllCount();

    List<Question> selectByIds(@Param("ids") List<Integer> ids);

    List<Question> page(QuestionPageRequest model);
}