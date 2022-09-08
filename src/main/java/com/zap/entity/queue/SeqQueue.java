package com.zap.entity.queue;

import java.lang.reflect.Array;

/**
 * @ClassName SeqQueue
 * @Author Evan
 * @Descrption
 * @create 2022/9/8 14:02
 */

public class SeqQueue<T>implements Queue<T> {
    private T[] arr;
    private int front; // 队头指针(索引值)
    private int rear; // 队尾指针(索引值)
    private int capacity; // 队列容量=数组长度-1
    private static final int MIN_CAPACITY = 3;

    public SeqQueue(int capacity, Class<T> c) {
        if (capacity <= MIN_CAPACITY) {
            capacity = MIN_CAPACITY;
        }
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) Array.newInstance(c, capacity + 1);
        this.arr=a;
        this.capacity = capacity;
        rear = 0;
        front = 0;
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    @Override
    public void add(T num) {
        if (isFull()) {
            System.out.println("队列已满!");
            return;
        }
        // rear = (rear+1) % 数组长度(队列容量+1)
        arr[rear] = num;
        rear = (rear + 1) % (capacity + 1);
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空!");
        }
        T data = arr[front];
        arr[front] = null;
        front = (front + 1) % (capacity + 1);
        return data;
    }

    @Override
    // 返回队列长度
    public int length() {
        return (rear - front + capacity + 1) % (capacity + 1);
    }

    @Override
    // 依次返回队列元素
    public void showInfo() {
        System.out.print("队列元素为:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println("");
    }

    // 队列是否已满
    @Override
    public  boolean isFull() {
        return front == (rear + 1) % (capacity + 1);
    }


    // 队列扩容
    // 主要工作在于把原来数组内容放到新数组中去, 但不能直接遍历原数组
    // 思路: 让原来的队列pop出所有值并添加到新的队列中
    public void capacityExpand() {

        capacity *= 2;
        final T[] newArr = (T[]) Array.newInstance(null, capacity + 1);
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;

        SeqQueue<T> temp = new SeqQueue<T>(capacity * 2, null);
        int originLength = this.length();
        for (int i = 0; i < originLength; i++) {    // 在循环过程中this.length会逐渐变小，不能直接用this.length()做循环条件
            T pop = this.poll();
            temp.add(pop);
        }
        // this = temp; 报错？不知道为啥
        // 那就依次把所有属性转移到本对象

        this.arr = temp.arr;
        this.capacity = temp.capacity;
        this.front = temp.front;
        this.rear = temp.rear;
    }

}
