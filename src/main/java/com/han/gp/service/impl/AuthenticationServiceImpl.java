package com.han.gp.service.impl;

import com.han.gp.domain.User;
import com.han.gp.service.AuthenticationService;
import com.han.gp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean authUser(User user, String username, String password) {
        if(null == user)
            return false;
        else if(!password.equals(user.getPassword())) {
            return false;
        }
        return true;
    }

    @Override
    public String pwdEncode(String password) {
        return password;
    }
}
