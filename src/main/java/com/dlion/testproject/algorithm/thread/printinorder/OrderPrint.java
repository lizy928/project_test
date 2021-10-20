package com.dlion.testproject.algorithm.thread.printinorder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 三个不同的线程 A、B、C 将会共用一个 Foo 实例。
 *
 * 一个将会调用 first() 方法
 * 一个将会调用 second() 方法
 * 还有一个将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lzy
 * @date 2021/10/8
 */
public class OrderPrint {

    public static void main(String[] args) {
        Foo foo = new Foo();
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

class Foo {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        atomicInteger.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        while (atomicInteger.get() != 1){

        }
        printSecond.run();
        atomicInteger.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        while (atomicInteger.get() != 2){

        }
        printThird.run();
    }
}
