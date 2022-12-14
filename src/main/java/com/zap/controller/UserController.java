package com.zap.controller;

import com.zap.common.Result;
import com.zap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/26 13:33
 */


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public Result sayHello(){
        return Result.ok().code(200).msg("你好哇");
    }

    @GetMapping("/getTree")
    public Result getTree(){
        List list = userService.getTree();
        return Result.ok().code(200).msg("你好哇").data("tree",list);
    }
}
