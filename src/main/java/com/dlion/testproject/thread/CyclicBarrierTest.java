package com.dlion.testproject.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 从字面上的意思可以知道，这个类的中文意思是“循环栅栏”。大概的意思就是一个可循环利用的屏障。
 * 它的作用就是会让所有线程都等待完成后才会继续下一步行动。
 * 举个例子，就像生活中我们会约朋友们到某个餐厅一起吃饭，有些朋友可能会早到，有些朋友可能会晚到，但是这个餐厅规定必须等到所有人到齐之后才会让我们进去。
 * 这里的朋友们就是各个线程，餐厅就是 CyclicBarrier。
 */
public class CyclicBarrierTest {

    /**
     * 一个线程组的线程需要等待所有线程完成任务后再继续执行下一次任务
     */
    @Test
    public void test() {
        //1、会议需要三个人
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                //2、这是三个人都到齐之后会执行的代码
                System.out.println("三个人都已到达会议室");
            }
        });

        //3、定义三个线程，相当于三个参会的人
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //4、模拟每人到会议室所需时间
                        // Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("第" + Thread.currentThread().getName() + "个人到达会议室");
                    try {
                        // 5、等待其他人到会议室
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "开始开会");
                }
            }, String.valueOf(i)).start();
        }

    }
}
