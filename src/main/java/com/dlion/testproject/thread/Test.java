package com.dlion.testproject.thread;

import java.util.concurrent.locks.ReentrantLock;

public class Test {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                hello();
            }
        });
        t1.setName("t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                hello();
            }
        });
        t2.setName("t2");

        t1.start();
        t2.start();

    }

    public static void hello(){
        try {
            Thread.sleep(100);
            lock.lock();
            System.out.println(Thread.currentThread().getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
