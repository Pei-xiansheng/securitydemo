package com.zap.service;

import com.zap.common.Result;
import com.zap.entity.Person;

/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/26 14:57
 */

public interface LoginService {
    Result login(Person person);
}
