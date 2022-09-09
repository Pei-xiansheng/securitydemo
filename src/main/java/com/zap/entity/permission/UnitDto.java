package com.zap.entity.permission;

import com.zap.entity.UnitPo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @ClassName UnitDto
 * @Author Evan
 * @Descrption
 * @create 2022/9/9 14:24
 */

@Data
public class UnitDto {

    private String id;
    private String pid;
    private String code;
    private String name;
    private String fullName;
    private String fullId;
    private String remark;

    private Integer level;

    private List<UnitDto> children;
}
