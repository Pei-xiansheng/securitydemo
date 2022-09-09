package com.zap.entity.design;

/**
 * @ClassName SystemC
 * @Author Evan
 * @Descrption
 * @create 2022/9/9 10:28
 */
class SystemC {

    public void Sc() {
        System.out.println("我是系统C!");
    }
}

/**
 * @ClassName SystemB
 * @Author Evan
 * @Descrption
 * @create 2022/9/9 10:28
 */
class SystemB {

    public void Sb() {
        System.out.println("我是系统B!");
    }
}

/**
 * @ClassName DoorFace
 * @Author Evan
 * @Descrption 门面模式
 * @create 2022/9/8 17:59
 */
class SystemA {

    public void Sa() {
        System.out.println("我是系统A!");
    }
}

/**
 * 门面
 */
public class DoorFace {

        private SystemC systemC = new SystemC();
        private SystemB systemB = new SystemB();
        private SystemA systemA = new SystemA();

        public void getSystemA(){
            this.systemA.Sa();
        }

        public void getSystemB(){
            this.systemB.Sb();
        }

        public void getSystemC(){
            this.systemC.Sc();
        }
}


