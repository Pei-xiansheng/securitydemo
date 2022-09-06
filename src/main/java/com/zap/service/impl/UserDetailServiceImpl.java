package com.zap.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zap.dao.AuthorityMapper;
import com.zap.dao.UserMapper;
import com.zap.entity.Authority;
import com.zap.entity.LoginUser;
import com.zap.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/26 14:28
 */

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthorityMapper authorityMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //从数据库中查询用户信息//索引是对数据库中一列或多列值进行排序的数据结构,
        LambdaQueryWrapper<Person> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Person::getUsername,name);
        Person person = userMapper.selectOne(queryWrapper);

        //判断是否存在此用户
        if(Objects.isNull(person)){
            throw new RuntimeException("用户名或密码出现错误了");
        }

        //TODO 数据库中查询用户权限信息
        Authority authority = authorityMapper.selectById(person.getId());
        List<String> authorityList = Arrays.stream(authority.getAuthority().split(",")).distinct().collect(Collectors.toList());
        return new LoginUser(person,authorityList);
    }
}
