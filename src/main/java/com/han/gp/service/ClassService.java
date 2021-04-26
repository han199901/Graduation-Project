package com.han.gp.service;

import com.github.pagehelper.PageInfo;
import com.han.gp.domain.Class;
import com.han.gp.vo.admin.education.ClassPageRequest;

import java.util.List;

public interface ClassService {
    Class selectById(Integer subjectId);

    List<Class> allClasses();

    int insertByFilter(Class aClass);

    int updateByIdFilter(Class aClass);

    PageInfo<Class> page(ClassPageRequest model);
}
