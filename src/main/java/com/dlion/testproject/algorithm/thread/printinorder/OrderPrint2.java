package com.dlion.testproject.algorithm.thread.printinorder;

/**
 * @author lzy
 * @date 2021/10/8
 */
public class OrderPrint2 {
    public static void main(String[] args) {
        Foo2 foo = new Foo2();
        new Thread(()->{
            try {
                foo.first(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println( Thread.currentThread().getName() + "--->first");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(()->{
            try {
                foo.second(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println( Thread.currentThread().getName() + "--->second");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(()->{
            try {
                foo.third(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println( Thread.currentThread().getName() + "--->third");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
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

