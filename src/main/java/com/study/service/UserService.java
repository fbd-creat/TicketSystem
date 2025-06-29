package com.study.service;

import com.study.bean.Tickets;
import com.study.bean.User;

import java.util.List;

/**
 * @author 朱天乐
 */
public interface UserService {

    boolean save(User user);

    User login(User user);


}
