package com.han.gp.service.impl;

import com.han.gp.domain.Class;
import com.han.gp.mapper.ClassMapper;
import com.han.gp.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
