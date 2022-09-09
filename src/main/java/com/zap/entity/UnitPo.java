package com.zap.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName UnitPo
 * @Author Evan
 * @Descrption
 * @create 2022/9/9 14:13
 */
@Data
@AllArgsConstructor
@TableName(value = "cm_acl_admin_unit")
public class UnitPo {
    private String id;
    private String pid;
    private String code;
    private String name;
    private String fullName;
    private String fullId;
    private String remark;
}
