package com.zap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.sql.Date;

/**
 * @ClassName Authority
 * @Author Evan
 * @Descrption
 * @create 2022/9/6 15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@TableName("blog_user_authority")
public class Authority {
    @TableId
    private Integer id;
    private Integer user_id;
    private String authority;
    private Date createTime;
    private Date updateTime;
}
