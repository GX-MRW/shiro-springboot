package com.sise.cofig;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author： wgx
 * @Date 2020-02-07-1:00.
 * @Description: com.sise.cofig
 * @verson：1.0
 */
@Configuration
public class ShiroConfig {

    //3.ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
        * anno: 无须认证就可以访问
        * authc：必须认证才可以访问
        * user：必须拥有 记住我功能才可以用
        * perms：拥有对某个资源的权限才能访问
        * role：拥有某个角色权限才可以访问
        *
        * //filterMap.put("/user/add","authc");
        * //filterMap.put("/user/update","authc");
        *
        * */
        Map<String, String> filterMap=new LinkedHashMap<>();
        //拦截
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        filterMap.put("/user/*","authc");

        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录的请求
        bean.setLoginUrl("/toLogin");
        //设置未授权页面
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }

    //2.DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        System.out.println(userRealm);
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //1.创建 realm 对象
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    //整合 ShiroDialect：用来整合shiro thymeleaf
    @Bean
    public ShiroDialect shiroDialect(){

        return new ShiroDialect();
    }

}
