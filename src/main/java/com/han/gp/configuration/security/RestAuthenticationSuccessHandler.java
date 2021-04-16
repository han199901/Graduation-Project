package com.han.gp.configuration.security;

import com.han.gp.base.SystemCode;
import com.han.gp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;

    @Autowired
    public RestAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User springUser = (User) authentication.getPrincipal();
        com.han.gp.domain.User user = userService.getUserByUserName(springUser.getUsername());
        com.han.gp.domain.User newUser = new com.han.gp.domain.User();
        newUser.setUserName(user.getUserName());
        RestUtil.response(response, SystemCode.OK.getCode(), SystemCode.OK.getMessage(), newUser);
    }
}
