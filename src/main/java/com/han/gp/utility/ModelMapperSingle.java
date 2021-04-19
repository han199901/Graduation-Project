package com.han.gp.utility;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Date;

public class ModelMapperSingle {
    protected final static ModelMapper modelMapper = new ModelMapper();
    private final static ModelMapperSingle modelMapperSingle = new ModelMapperSingle();

    static {
        modelMapper.createTypeMap(String.class, Date.class).setConverter(mappingContext -> {
            String source = mappingContext.getSource();
            return DateTimeUtil.parse(source,"yyyy-MM-dd HH:mm:ss");
        });
        modelMapper.getConfiguration().setFullTypeMatchingRequired(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static ModelMapper Instance() {
        return modelMapperSingle.modelMapper;
    }
}
