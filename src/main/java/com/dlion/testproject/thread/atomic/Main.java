package com.dlion.testproject.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic 翻译成中文是原子的意思。在化学上，我们知道原子是构成一般物质的最小单位，在化学反应中是不可分割的。在我们这里 Atomic 是指一个操作是不可中断的。
 * 即使是在多个线程一起执行的时候，一个操作一旦开始，就不会被其他线程干扰。
 *
 * 所以，所谓原子类说简单点就是具有原子/原子操作特征的类
 *
 * @author lzy
 * @date 2020/9/23
 */
public class Main {


    /**
     * 使用 AtomicInteger 之后，不用对 increment() 方法加锁也可以保证线程安全。
     */
    class AtomicIntegerTest {
        private AtomicInteger count = new AtomicInteger();
        //使用AtomicInteger之后，不需要对该方法加锁，也可以实现线程安全。
        public void increment() {
            count.incrementAndGet();
        }

        public int getCount() {
            return count.get();
        }
    }
}
