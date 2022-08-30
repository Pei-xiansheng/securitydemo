package com.zap.controller;

import com.zap.common.Result;
import com.zap.config.RedisService;
import com.zap.entity.LoginUser;
import com.zap.entity.Person;
import com.zap.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/26 14:58
 */

@RestController
public class LoginOutController {

    private static String LOGIN_REDIS_KEY="login:";

    @Autowired
    LoginService loginService;


    @Autowired
    RedisService redisService;

    @PostMapping("/userLogin")
    public Result userLogin(@RequestBody Person person){
        return loginService.login(person);
    }


    @GetMapping("/userLogOut")
    public Result userLogOut(){
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        //获取用户信息，清除用户的登录态
        LoginUser loginUser = (LoginUser) token.getPrincipal();
        Integer userId = loginUser.getPerson().getId();
        redisService.del(LOGIN_REDIS_KEY+userId);
        return Result.ok().msg("用户注销成功！");
    }
}
