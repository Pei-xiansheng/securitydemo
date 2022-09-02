package com.zap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;


/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/26 14:05
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@TableName("blog_user")
public class Person{

    @TableId
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private String email;
    private String phone;
    private String gender;
    private Date createTime;
    private Date updateTime;
}
