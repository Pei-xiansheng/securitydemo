package com.zap.entity.queue;

/**
 * @ClassName Queue
 * @Author Evan
 * @Descrption
 * @create 2022/9/8 14:01
 */
public interface Queue<T> {
    // 队列是否空
    public abstract boolean isEmpty();

    // 入队,添加成功返回true, 不成功返回false
    public abstract void add(T num);

    // 出队
    public abstract T poll();

    // 返回队列长度
    public abstract int length();

    // 展示队列元素
    public abstract void showInfo();

    // 队列是否满
    public abstract boolean isFull();

}
