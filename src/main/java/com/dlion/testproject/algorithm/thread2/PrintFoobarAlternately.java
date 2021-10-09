package com.dlion.testproject.algorithm.thread2;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 我们提供一个类：
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 *
 *
 * https://leetcode-cn.com/problems/print-foobar-alternately/
 */
public class PrintFoobarAlternately {

    public static void main(String[] args) {

        FooBar4 fooBar = new FooBar4(10);//打印10次foo bar
        Runnable printFoo = () -> {
            System.out.printf("%s\n", "foo");
        };
        Runnable printBar = () -> {
            System.out.printf("%s\n", "bar");
        };
        Thread fooThread = new Thread(() -> {
            try {
                fooBar.foo(printFoo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread barThread = new Thread(() -> {
            try {
                fooBar.bar(printBar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        fooThread.start();
        barThread.start();

    }
}

class FooBar4 {
    private int n;

    private CyclicBarrier cb = new CyclicBarrier(2);
    volatile boolean fooExec = true;

    public FooBar4(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!fooExec){

            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            fooExec = false;
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // printBar.run() outputs "bar". Do not change or remove this line.
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            printBar.run();
            fooExec = true;
        }
    }
}


