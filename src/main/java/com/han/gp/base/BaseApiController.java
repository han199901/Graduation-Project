package com.han.gp.base;

import com.han.gp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseApiController {
    protected final static String DEFAULT_PAGE_SIZE = "10";
    @Autowired
    protected WebContext webContext;

    protected User getCurrentUser() {
        return webContext.getCurrentUser();
    }
}