package com.zap;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zap.dao.UserMapper;
import com.zap.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/26 14:09
 */

@SpringBootTest
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test(){

        LambdaQueryWrapper<Person> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Person::getName,"老王");
        Person person = userMapper.selectOne(queryWrapper);
        System.out.println(person);
    }

    @Test
    public void getPassWord(){
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }


}
