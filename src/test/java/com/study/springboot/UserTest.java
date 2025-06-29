package com.study.springboot;

import com.study.bean.User;
import com.study.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author 朱天乐
 */
@SpringBootTest
public class UserTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void t1() {
        User user = userMapper.selectUserById(1);

        System.out.println(user);
    }
}
