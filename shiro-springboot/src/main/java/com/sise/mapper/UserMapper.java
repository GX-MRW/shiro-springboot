package com.sise.mapper;

import com.sise.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author： wgx
 * @Date 2020-02-07-16:24.
 * @Description: com.sise.mapper
 * @verson：1.0
 */
@Mapper
@Repository
public interface UserMapper {

    public User queryUserByName(String username);

    public List<User> queryUser();

    String getPermsByName(String name);
}
