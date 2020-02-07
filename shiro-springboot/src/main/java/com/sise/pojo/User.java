package com.sise.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Author： wgx
 * @Date 2020-02-07-16:23.
 * @Description: com.sise.pojo
 * @verson：1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
    private  Integer id;
    private  String username;
    private  String password;
    private  String perms;
}

