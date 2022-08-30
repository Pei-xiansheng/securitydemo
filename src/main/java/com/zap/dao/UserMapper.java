package com.zap.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zap.entity.Person;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/26 14:08
 */

@Mapper
public interface UserMapper extends BaseMapper<Person> {
}
