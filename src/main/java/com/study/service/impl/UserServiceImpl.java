package com.study.service.impl;

import com.study.bean.User;
import com.study.mapper.UserMapper;
import com.study.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 朱天乐
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean save(User user) {
        int i = userMapper.insertUser(user);
        return i == 1 ? true : false;
    }

    @Override
    public User login(User user) {
        User login = userMapper.login(user);
        return login;
    }
}
