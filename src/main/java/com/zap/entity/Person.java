package com.zap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@TableName("user")
public class Person {

    @TableId
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private Date createTime;
    private Date updateTime;
    private Integer version;
    private Integer deleted;
    private String password;
}
