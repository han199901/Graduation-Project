package com.han.gp.service;

import com.han.gp.domain.User;

public interface AuthenticationService {
    boolean authUser(User user, String username, String password);

    String pwdEncode(String password);
}
