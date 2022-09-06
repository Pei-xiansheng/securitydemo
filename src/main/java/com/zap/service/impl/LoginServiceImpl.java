package com.zap.service.impl;

import com.zap.common.Result;
import com.zap.config.RedisService;
import com.zap.entity.LoginUser;
import com.zap.entity.Person;
import com.zap.entity.event.UserRegisterEvent;
import com.zap.service.LoginService;
import com.zap.utils.JwtUtils;
import com.zap.utils.SpringContextUtil;
import org.apache.coyote.http11.filters.VoidInputFilter;
import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Objects;

import static io.lettuce.core.pubsub.PubSubOutput.Type.message;

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

    @Autowired
    KafkaTemplate template;

    @Autowired
    SpringContextUtil springContextUtil;

    private static String LOGIN_REDIS_KEY="login:";

    @Override
    public Result login(Person person) {
        //用户认证
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(person.getUsername(), person.getPassword()));

        //判断是否通过认证
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败！");
        }
        //发布事件
        SpringContextUtil.publishEvent(new UserRegisterEvent("你好呀!",Long.valueOf(88L)));

        //发送消息
        sendMsg();
        //获取认证后的用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        //生成JWT字符串保存用户的信息
        Integer perId = loginUser.getPerson().getId();
        String token = JwtUtils.getJwtToken(perId);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        //将用完整的用户信息存入redis中

        redisService.set(LOGIN_REDIS_KEY+perId,loginUser,5);
        return Result.ok().map(map);
    }
    public void sendMsg(){
        ListenableFuture<SendResult<String, Message>> future = template.send("ap_kafka_test","test_kafka", "用户认证成功!");
        future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, Message> result) {
                System.out.println(result.getProducerRecord());
                System.out.println(result.getRecordMetadata());
            }
        });

    }
}
