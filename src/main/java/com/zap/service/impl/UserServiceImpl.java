package com.zap.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.zap.dao.UnitMapper;
import com.zap.entity.UnitPo;
import com.zap.entity.permission.UnitDto;
import com.zap.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author Evan
 * @Descrption
 * @create 2022/9/9 14:11
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UnitMapper unitMapper;

    @Override
    public List getTree() {
        List<UnitPo> poList = unitMapper.selectList(new QueryWrapper<>());
        ArrayList<UnitDto> beginList = new ArrayList<>();
        for (UnitPo up:poList
             ) {
            UnitDto ud = new UnitDto();
            BeanUtils.copyProperties(up,ud,"children","level");
            beginList.add(ud);
        }
        //封装集合
        List<UnitDto> perList = buildPermission(beginList);
        for (UnitDto ud:perList
             ) {
            System.out.println(ud);
        }
        return perList;
    }


    private List<UnitDto> buildPermission(List<UnitDto> poList) {
        ArrayList<UnitDto> finalPerList = new ArrayList<>();
        for (UnitDto ud:poList
             ) {
            if("0".equals(ud.getPid())){
                //设置顶级菜单层级
                ud.setLevel(1);
                //设置当前节点的子节点信息
                finalPerList.add(selectChildren(ud,poList));
            }
        }
        return finalPerList;
    }

    private UnitDto selectChildren(UnitDto ud, List<UnitDto> poList) {
        List<UnitDto> dtoList = new ArrayList<>();
        ud.setChildren(dtoList);
        for (UnitDto udChildren:poList
             ) {
            if(ud.getId().equals(udChildren.getPid())){
                Integer level = ud.getLevel()+1;
                udChildren.setLevel(level);
                ud.getChildren().add(selectChildren(udChildren,poList));
            }
        }
        return ud;
    }
}
