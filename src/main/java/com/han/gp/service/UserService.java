package com.han.gp.service;

import com.github.pagehelper.PageInfo;
import com.han.gp.domain.User;
import com.han.gp.vo.teacher.user.UserPageRequest;

public interface UserService {
    User getUserByUserName(String username);

    PageInfo<User> userPage(com.han.gp.vo.admin.user.UserPageRequest model);

    User getUserById(Integer id);

    void insertSelective(User user);

    void updateByPrimaryKeySelective(User user);

    User selectById(Integer id);

    void changePicture(User currentUser, String filePath);

    PageInfo<User> userPageByTeacher(UserPageRequest model);
}
