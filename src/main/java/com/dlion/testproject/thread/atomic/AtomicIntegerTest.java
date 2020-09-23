package com.dlion.testproject.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 类主要利用 CAS (compare and swap) + volatile 和 native 方法来保证原子操作，从而避免 synchronized 的高开销，执行效率大为提升。
 *
 * CAS的原理是拿期望的值和原本的一个值作比较，如果相同则更新成新的值。UnSafe 类的 objectFieldOffset() 方法是一个本地方法，这个方法是用来拿到“原来的值”的内存地址，返回值是 valueOffset。
 * 另外 value 是一个volatile变量，在内存中可见，因此 JVM 可以保证任何时刻任何线程总能拿到该变量的最新值。
 *
 * @author lzy
 * @date 2020/9/23
 */
public class AtomicIntegerTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int temvalue = 0;
        AtomicInteger i = new AtomicInteger(0);
        AtomicInteger i2 = new AtomicInteger();
        temvalue = i.getAndSet(3);
        //temvalue:0;  i:3
        System.out.println("temvalue:" + temvalue + ";  i:" + i);
        temvalue = i.getAndIncrement();
        //temvalue:3;  i:4
        System.out.println("temvalue:" + temvalue + ";  i:" + i);
        temvalue = i.getAndAdd(5);
        //temvalue:4;  i:9
        System.out.println("temvalue:" + temvalue + ";  i:" + i);
    }

    /**
     * ①多线程环境不使用原子类保证线程安全（基本数据类型）
     */
    class Test {
        private volatile int count = 0;
        //若要线程安全执行执行count++，需要加锁
        public synchronized void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    /**
     * ②多线程环境使用原子类保证线程安全（基本数据类型）
     */
    class Test2 {
        private AtomicInteger count = new AtomicInteger();

        public void increment() {
            count.incrementAndGet();
        }
        //使用AtomicInteger之后，不需要加锁，也可以实现线程安全。
        public int getCount() {
            return count.get();
        }
    }

}
