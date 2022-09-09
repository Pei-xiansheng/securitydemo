package com.zap.entity.design;

/**
 * @ClassName Strategy
 * @Author Evan
 * @Descrption 策略
 * @create 2022/9/9 10:41
 */
public abstract class Strategy {
    public abstract void getSystem();
}

class StrategyA extends Strategy{

    @Override
    public void getSystem() {
        System.out.println("我是策略A");
    }
}

class StrategyB extends Strategy{

    @Override
    public void getSystem() {
        System.out.println("我是策略B");
    }
}
