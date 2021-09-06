package com.dlion.testproject.thread;

/**
 * @author lizy
 * @date 2021/7/9 15:07
 */
public class DeadLockTest {

    static Object a = new Object();
    static Object b = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            synchronized (a){
                System.out.println(Thread.currentThread().getName() + "持有锁A");
                synchronized (b){
                    System.out.println(Thread.currentThread().getName() + "尝试获取锁B");
                }
            }
        }, "A").start();

        new Thread(()->{
            synchronized (b){
                System.out.println(Thread.currentThread().getName() + "持有锁B");
                synchronized (a){
                    System.out.println(Thread.currentThread().getName() + "尝试获取锁A");
                }
            }
        }, "B").start();
    }

}