package com.dlion.testproject.algorithm.thread.printinorder;

import java.util.concurrent.Semaphore;

/**
 * @author lzy
 * @date 2021/10/8
 */
public class OrderPrint3 {

}

class Foo3 {

    private Semaphore two = new Semaphore(0);
    private Semaphore three = new Semaphore(0);

    public Foo3() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        two.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        two.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        three.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        three.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
