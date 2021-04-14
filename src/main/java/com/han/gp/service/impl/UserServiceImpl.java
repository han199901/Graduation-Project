package com.han.gp.service.impl;

import com.han.gp.domain.User;
import com.han.gp.mapper.UserMapper;
import com.han.gp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public User getUserByUserName(String username) {
        return mapper.selectByUserName(username);
    }
}
