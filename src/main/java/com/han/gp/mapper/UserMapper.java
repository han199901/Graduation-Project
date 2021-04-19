package com.han.gp.mapper;

import com.github.pagehelper.PageInfo;
import com.han.gp.domain.User;
import com.han.gp.vo.admin.user.UserPageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUserName(String userName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    List<User> userPage(UserPageRequest model);
}