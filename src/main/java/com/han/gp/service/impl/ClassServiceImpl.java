package com.han.gp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.gp.domain.Class;
import com.han.gp.mapper.ClassMapper;
import com.han.gp.service.ClassService;
import com.han.gp.vo.admin.education.ClassPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassMapper classMapper;

    @Autowired
    public ClassServiceImpl(ClassMapper classMapper) {
        this.classMapper = classMapper;
    }

    @Override
    public Class selectById(Integer subjectId) {
        return classMapper.selectByPrimaryKey(subjectId);
    }

    @Override
    public List<Class> allClasses() {
        return classMapper.allClasses();
    }

    @Override
    public int insertByFilter(Class aClass) {
        return classMapper.insertSelective(aClass);
    }

    @Override
    public int updateByIdFilter(Class aClass) {
        return classMapper.updateByPrimaryKeySelective(aClass);
    }

    @Override
    public PageInfo<Class> page(ClassPageRequest model) {
        return PageHelper.startPage(model.getPageIndex(), model.getPageSize(), "id desc").doSelectPageInfo(() ->
                classMapper.page(model)
        );
    }

}
