package com.zap.service.impl;

import com.zap.common.Result;
import com.zap.config.RedisService;
import com.zap.entity.LoginUser;
import com.zap.entity.Person;
import com.zap.service.LoginService;
import com.zap.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/26 14:57
 */

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisService redisService;


    private static String LOGIN_REDIS_KEY="login:";

    @Override
    public Result login(Person person) {
        //用户认证
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(person.getUsername(), person.getPassword()));

        //判断是否通过认证
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败！");
        }
        //获取认证后的用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        //生成JWT字符串保存用户的信息
        Integer perId = loginUser.getPerson().getId();
        String token = JwtUtils.getJwtToken(perId);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        //将用完整的用户信息存入redis中

        redisService.set(LOGIN_REDIS_KEY+perId,loginUser);
        return Result.ok().map(map);
    }
}
