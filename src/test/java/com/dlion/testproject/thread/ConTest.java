package com.dlion.testproject.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition是一个多线程间协调通信的工具类，使得某个，或者某些线程一起等待某个条件（Condition）,
 * 只有当该条件具备( signal 或者 signalAll方法被带调用)时 ，这些等待线程才会被唤醒，从而重新争夺锁。
 * <p>
 * <p>
 * Condition的执行方式，是当在线程Consumer中调用await方法后，线程Consumer将释放锁，并且将自己沉睡，等待唤醒，线程Producer获取到锁后，开始做事，
 * 完毕后，调用Condition的signalall方法，唤醒线程Consumer，线程Consumer恢复执行。
 *
 * @author lizy
 * @date 2021/9/8 14:03
 */
public class ConTest {

    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ConTest test = new ConTest();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        consumer.start();
        producer.start();
    }

    class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }

        private void consume() {

            try {
                lock.lock();
                System.out.println("我在等一个新信号" + this.currentThread().getName());
                condition.await();

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                System.out.println("拿到一个信号" + this.currentThread().getName());
                lock.unlock();
            }

        }
    }

    class Producer extends Thread {

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            try {
                lock.lock();
                System.out.println("我拿到锁" + this.currentThread().getName());
                condition.signalAll();
                System.out.println("我发出了一个信号：" + this.currentThread().getName());
            } finally {
                lock.unlock();
            }
        }
    }

}