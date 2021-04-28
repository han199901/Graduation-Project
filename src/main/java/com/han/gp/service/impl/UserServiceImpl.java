package com.han.gp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.gp.domain.User;
import com.han.gp.mapper.UserMapper;
import com.han.gp.service.UserService;
import com.han.gp.vo.teacher.user.UserPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final static String CACHE_NAME = "user";
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public User getUserByUserName(String username) {
        return mapper.selectByUserName(username);
    }

    @Override
    public PageInfo<User> userPage(com.han.gp.vo.admin.user.UserPageRequest model) {
        return PageHelper.startPage(model.getPageIndex(), model.getPageSize(), "id desc").doSelectPageInfo(() ->
                mapper.userPage(model)
        );
    }

    @Override
    public User getUserById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void insertSelective(User user) {
        mapper.insertSelective(user);
    }

    @Override
    public void updateByPrimaryKeySelective(User user) {
        mapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void changePicture(User currentUser, String filePath) {
        User changePictureUser = new User();
        changePictureUser.setId(currentUser.getId());
        changePictureUser.setImagePath(filePath);
        mapper.updateByPrimaryKeySelective(changePictureUser);
    }

    @Override
    public PageInfo<User> userPageByTeacher(UserPageRequest model) {
        return PageHelper.startPage(model.getPageIndex(), model.getPageSize(), "id desc").doSelectPageInfo(() ->
                mapper.userPageByTeacher(model)
        );
    }

}
