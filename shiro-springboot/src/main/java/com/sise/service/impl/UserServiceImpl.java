package com.sise.service.impl;

import com.sise.mapper.UserMapper;
import com.sise.pojo.User;
import com.sise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author： wgx
 * @Date 2020-02-07-16:34.
 * @Description: com.sise.service.impl
 * @verson：1.0
 */
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByname(String username) {
        return userMapper.queryUserByName(username);
    }

    @Override
    public List<User> queryUser() {
        return userMapper.queryUser();
    }

    @Override
    public String getPermsByName(String name) {
        return userMapper.getPermsByName(name);
    }
}
