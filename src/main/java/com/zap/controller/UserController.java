package com.zap.controller;

import com.zap.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/26 13:33
 */


@RestController
public class UserController {

    @GetMapping("/hello")
    public Result sayHello(){
        return Result.ok().code(200).msg("你好哇");
    }
}
