package com.han.gp.service.impl;

import com.han.gp.domain.TextContent;
import com.han.gp.mapper.TextContentMapper;
import com.han.gp.service.TextContentService;
import com.han.gp.utility.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TextContentServiceImpl implements TextContentService {
    private final static String CACHE_NAME = "textcontent";
    private final TextContentMapper textContentMapper;

    @Autowired
    public TextContentServiceImpl(TextContentMapper textContentMapper) {
        this.textContentMapper = textContentMapper;
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "#id", unless = "#result == null")
    public TextContent selectById(Integer id) {
        return textContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertByFilter(TextContent record) {
        return textContentMapper.insertSelective(record);
    }

    @Override
    @CacheEvict(value = CACHE_NAME, key = "#record.id")
    public int updateByIdFilter(TextContent record) {
        return textContentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public <T, R> TextContent jsonConvertInsert(List<T> list, Date now, Function<? super T, ? extends R> mapper) {
        String frameTextContent = null;
        if (null == mapper) {
            frameTextContent = JsonUtil.toJsonStr(list);
        } else {
            List<R> mapList = list.stream().map(mapper).collect(Collectors.toList());
            frameTextContent = JsonUtil.toJsonStr(mapList);
        }
        TextContent textContent = new TextContent(frameTextContent, now);
        //insertByFilter(textContent);  cache useless
        return textContent;
    }

    @Override
    public <T, R> TextContent jsonConvertUpdate(TextContent textContent, List<T> list, Function<? super T, ? extends R> mapper) {
        String frameTextContent = null;
        if (null == mapper) {
            frameTextContent = JsonUtil.toJsonStr(list);
        } else {
            List<R> mapList = list.stream().map(mapper).collect(Collectors.toList());
            frameTextContent = JsonUtil.toJsonStr(mapList);
        }
        textContent.setContent(frameTextContent);
        //this.updateByIdFilter(textContent);  cache useless
        return textContent;
    }
}
