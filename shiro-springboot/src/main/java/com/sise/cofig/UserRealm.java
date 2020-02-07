package com.sise.cofig;

import com.sise.pojo.User;
import com.sise.service.UserService;
import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.util.Password;

import java.security.Security;

/**
 * @Author： wgx
 * @Date 2020-02-07-13:31.
 * @Description: com.sise.cofig
 * @verson：1.0
 */
//自定义的 UserRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权=>AuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");

        //获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        //拿到user对象
        User currentUser = (User) subject.getPrincipal();
        //设置当前用户的授权
        info.addStringPermission(currentUser.getPerms());

        return info;
    }
    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了身份认证=>AuthenticationInfo");

        UsernamePasswordToken userToken=(UsernamePasswordToken) token;
        User user = userService.queryUserByname(userToken.getUsername());

        if(user==null){
            return null; //UnknownAccountException
        }

        //密码认证，shiro做, 加密
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");//存放的主体,密码, String realmName
    }
}
