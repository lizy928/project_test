package com.dlion.testproject.algorithm.thread;

/**
 * @author lzy
 * @date 2021/10/8
 */
public class OrderPrint2 {


}

class Foo2 {

    public Foo2() {

    }
    volatile int i = 1;
    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        i++;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(i!=2) {
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        i++;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(i!=3) {
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        i=1;
    }
}

