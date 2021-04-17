package com.han.gp.mapper;

import com.han.gp.domain.TextContent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TextContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TextContent record);

    int insertSelective(TextContent record);

    TextContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TextContent record);

    int updateByPrimaryKeyWithBLOBs(TextContent record);

    int updateByPrimaryKey(TextContent record);
}