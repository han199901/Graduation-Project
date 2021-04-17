package com.han.gp.mapper;

import com.han.gp.domain.Class;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
}