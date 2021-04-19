package com.han.gp.vo.admin;

import com.han.gp.utility.ModelMapperSingle;
import org.modelmapper.ModelMapper;

public class Base {
    protected static ModelMapper modelMapper = ModelMapperSingle.Instance();


    public static ModelMapper getModelMapper() {
        return modelMapper;
    }

    public static void setModelMapper(ModelMapper modelMapper) {
        Base.modelMapper = modelMapper;
    }
}
