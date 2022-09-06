package com.zap;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zap.dao.UserMapper;
import com.zap.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
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
        queryWrapper.eq(Person::getUsername,"张三");
        Person person = userMapper.selectOne(queryWrapper);
        System.out.println(person);

    }

    @Test
    public void getPassWord(){
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }
    @Test
    public void bubble(){
        int[] arr = new int[]{1,55,20,3,99,56,82,12};


        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        Arrays.stream(arr).forEach(item->{
            System.out.println(item);
        });
    }


}
