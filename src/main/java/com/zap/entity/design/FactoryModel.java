package com.zap.entity.design;



/**
 * @ClassName FactoryModel
 * @Author Evan
 * @Descrption 工厂
 * @create 2022/9/9 10:45
 */
interface ShoeNike{
    public void produceNike();
}

interface ShoeLiNing{
    public void produceLiNing();
}

class Nike implements ShoeNike{
    @Override
    public void produceNike() {
        System.out.println("正在生产Nike!!!");
    }
}

class LiNing implements ShoeLiNing{
    @Override
    public void produceLiNing(){
        System.out.println("正在生产LiNing!!!");
    }
}

abstract class ProxyFactory{

    public void init(){
        System.out.println("初始化基本原材料!");
    }

    public abstract ShoeNike produceNike();

    public abstract ShoeLiNing produceLiNing();
}

public class FactoryModel extends ProxyFactory{

    @Override
    public ShoeNike produceNike() {
        super.init();
        return new Nike();
    }

    @Override
    public ShoeLiNing produceLiNing() {
        super.init();
        return new LiNing();
    }
}
