package com.zap.listener;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zap.dao.UserMapper;
import com.zap.entity.Person;
import com.zap.entity.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserRegisterEventListener
 * @Author Evan
 * @Descrption 用户注册的事件监听
 * @create 2022/9/6 11:01
 */
@Slf4j
@Component
public class UserRegisterEventListener {

    @Autowired
    UserMapper userMapper;

    /**
     * 用户注册事件监听
     *
     * @param event
     */
    @EventListener
    public void userRegisterEvent(UserRegisterEvent event) {
        LambdaQueryWrapper<Person> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Person::getUsername,"张三");
        Person person = userMapper.selectOne(queryWrapper);
        System.out.println(person);

        log.info("获取到的事件,用户ID{},传递事件内容{}",event.getUserId(),event.getSource());
    }
}
