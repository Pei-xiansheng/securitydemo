package com.zap.entity.design;

/**
 * @ClassName Template
 * @Author Evan
 * @Descrption 模板模式
 * @create 2022/9/9 11:16
 */

public abstract class TemplateModel {

    //抽象参数
    public abstract void dealUserName();
    public abstract void dealPassword();
    public abstract void dealDriverManager();

    //初始化数据
    public void initData(){
        dealUserName();
        dealPassword();
        dealDriverManager();
    }
}

/**
 * 子类具体实现
 */
class JdbcTemplate extends TemplateModel{

    @Override
    public void dealUserName() {
        System.out.println("我是Jdbc用户名");
    }

    @Override
    public void dealPassword() {
        System.out.println("我是Jdbc密码");
    }

    @Override
    public void dealDriverManager() {
        System.out.println("我是Jdbc驱动");
    }

}

/**
 * 子类具体实现
 */
class MybatisTemplate extends TemplateModel{

    @Override
    public void dealUserName() {
        System.out.println("我是Mybatis用户名");
    }

    @Override
    public void dealPassword() {
        System.out.println("我是Mybatis密码");
    }

    @Override
    public void dealDriverManager() {
        System.out.println("我是Mybatis驱动");
    }

}
