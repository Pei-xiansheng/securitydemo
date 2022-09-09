package com.zap.entity.design;

/**
 * @ClassName Design
 * @Author Evan
 * @Descrption 装饰者模式
 * @create 2022/9/9 11:23
 */

/**
 * 抽象接口
 */
interface Friends{
    void doSomething();
}

/**
 * 朋友Evan
 */
class Evan implements Friends{
    @Override
    public void doSomething() {
        System.out.println("我是你的好朋友Evan");
    }
}

/**
 * 朋友刘德华
 */
class LiuDeHUA implements Friends{
    @Override
    public void doSomething() {
        System.out.println("我是你的好朋友刘德华");
    }
}

/**
 * 装饰Friends
 */
class Decorate implements Friends{

    public Friends friends;

    public Decorate(Friends friends){
        this.friends=friends;
    }

    @Override
    public void doSomething() {
        friends.doSomething();
    }
}

class Car extends Decorate{

    public Car(Friends friends) {
        super(friends);
    }
    @Override
    public void doSomething() {
        friends.doSomething();
        System.out.println("开了一辆超级大炮车!");
    }

}

class Beauty extends Decorate{

    public Beauty(Friends friends) {
        super(friends);
    }
    @Override
    public void doSomething() {
        friends.doSomething();
        System.out.println("带了一车漂亮的妹子!");
    }

}

public class DecorateModel{

    public static void main(String[] args) {
        Friends friends = new Beauty(new Car(new Evan()));
        friends.doSomething();
    }
}

