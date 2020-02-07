package com.sise;

import com.sise.pojo.User;
import com.sise.service.UserService;
import com.sise.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShiroSpringbootApplicationTests {
    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
        User wgx = userService.queryUserByname("aaa");
        System.out.println(wgx);

        List<User> users = userService.queryUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

}
