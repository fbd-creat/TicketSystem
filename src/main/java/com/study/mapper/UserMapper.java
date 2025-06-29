package com.study.mapper;

import com.study.bean.User;

import java.util.List;

/**
 * @author 朱天乐
 */
public interface UserMapper {

    List<User> selectAllUsers();

    User selectUserById(Integer id);

    User login(User user);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUserById(Integer id);
}
