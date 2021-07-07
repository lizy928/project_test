package com.dlion.testproject.thread;

public class YieldTest implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        YieldTest yieldTest = new YieldTest();

        Thread thread1 = new Thread(yieldTest, "firse:");
        Thread thread2 = new Thread(yieldTest, "second");

        thread1.start();
        thread2.start();
    }
}
