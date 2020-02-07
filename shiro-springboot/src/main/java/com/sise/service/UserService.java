package com.sise.service;

import com.sise.pojo.User;

import java.util.List;

/**
 * @Author： wgx
 * @Date 2020-02-07-16:34.
 * @Description: com.sise.service
 * @verson：1.0
 */
public interface UserService {
    User queryUserByname(String username);

    List<User> queryUser();

    String getPermsByName(String name);
}
