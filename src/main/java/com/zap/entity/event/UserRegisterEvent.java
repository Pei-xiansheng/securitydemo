package com.zap.entity.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName UserRegisterEvent
 * @Author Evan
 * @Descrption 定义事件
 * @create 2022/9/6 10:59
 */

public class UserRegisterEvent extends ApplicationEvent {

    /**
     * 用户id
     */
    private Long userId;

    public UserRegisterEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

