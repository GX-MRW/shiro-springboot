package com.sise.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author： wgx
 * @Date 2020-02-07-0:09.
 * @Description: com.sise.controller
 * @verson：1.0
 */
@Controller
public class MyController {

    private static final transient Logger log = LoggerFactory.getLogger(MyController.class);
    /**
     * 功能描述: 跳转到index页面
     */
    @RequestMapping({"/","/index.html"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello,shiro");
        return "index";
    }
    /**
     * 功能描述: 跳转到add页面
     */
    @RequestMapping("/user/add")
    public String toAdd(){
        return "user/add";
    }
    /**
     * 功能描述: 跳转到update页面
     */
    @RequestMapping("/user/update")
    public String toUpdate(){
        return "user/update";
    }
    /**
     * 功能描述:  跳转到登录页面
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    /**
     * 功能描述:  用户登录
     */
    @RequestMapping("/login")
    public String toLogin(String username,String password,Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            //执行登录操作
            subject.login(token);
            return "index";
        } catch (UnknownAccountException uae) {
            log.info("用户名不存在 = > " + token.getPrincipal());
            model.addAttribute("msg","用户名错误");
            return "login";
        } catch (IncorrectCredentialsException ice) {
            log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
    /**
     * 功能描述:  跳转到未授权页面页面
     */
    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized(){
        return "未经授权无法访问次页面！";
    }
}
