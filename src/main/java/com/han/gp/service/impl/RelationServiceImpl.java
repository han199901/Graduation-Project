package com.han.gp.service.impl;

import com.han.gp.domain.Relation;
import com.han.gp.mapper.RelationMapper;
import com.han.gp.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationServiceImpl implements RelationService {

    private final RelationMapper relationMapper;

    @Autowired
    public RelationServiceImpl(RelationMapper relationMapper) {
        this.relationMapper = relationMapper;
    }

    @Override
    public Integer getClassIdByUid(Integer id) {
        Relation relation = relationMapper.selectByUid(id);
        return relation.getCid();
    }
}
