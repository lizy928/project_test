package com.dlion.testproject.thread;


import org.junit.jupiter.api.Test;

/**
 * wait
 * <p>
 * <p>
 * sleep
 * <p>
 * <p>
 * notify
 */
public class WaitTest {

    @Test
    public void waitTest() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                try {
                    System.out.println("Start-----");
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait end....");
            }
        });
        thread.start();
    }

    public synchronized void testWait() {//增加Synchronized关键字
        System.out.println(Thread.currentThread().getName() +" Start-----");
        try {
            wait(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +" End-------");
    }

    public static void main(String[] args) {
        WaitTest test = new WaitTest();
        for (int i = 0; i < 5; i++) {
            new Thread(test::testWait).start();
        }

        /**
         * 要在同一对象上去调用notify/notifyAll方法，就可以唤醒对应对象monitor上等待的线程了
         */
        synchronized (test){
            test.notify();
        }

        System.out.println("-----------分割线-------------");

        synchronized (test){
            test.notifyAll();
        }

    }


}
