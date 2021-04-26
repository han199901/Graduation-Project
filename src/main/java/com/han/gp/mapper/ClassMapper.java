package com.han.gp.mapper;


import com.han.gp.domain.Class;
import com.han.gp.vo.admin.education.ClassPageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);

    List<Class> allClasses();

    List<Class> page(ClassPageRequest model);
}