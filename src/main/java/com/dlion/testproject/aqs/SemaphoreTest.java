package com.dlion.testproject.aqs;

import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.Semaphore;

/**
 *用semaphore 实现停车场提示牌功能。
 * 每个停车场入口都有一个提示牌，上面显示着停车场的剩余车位还有多少，当剩余车位为0时，不允许车辆进入停车场，直到停车场里面有车离开停车场，这时提示牌上会显示新的剩余车位数。
 *
 * 1、停车场容纳总停车量10。
 * 2、当一辆车进入停车场后，显示牌的剩余车位数响应的减1.
 * 3、每有一辆车驶出停车场后，显示牌的剩余车位数响应的加1。
 * 4、停车场剩余车位不足时，车辆只能在外面等待。
 *
 */
public class SemaphoreTest {

    @Test
    public void test(){
        //停车场同时容纳的车辆10
        Semaphore semaphore=new Semaphore(10);
        //模拟100辆车进入停车场
        for (;;) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println("====" + Thread.currentThread().getName() + "来到停车场");
                    if (semaphore.availablePermits() == 0) {
                        System.out.println("车位不足，请耐心等待");
                    }
                    semaphore.acquire();//获取令牌尝试进入停车场
                    System.out.println(Thread.currentThread().getName() + "成功进入停车场");
                    Thread.sleep(1000);//模拟车辆在停车场停留的时间
                    System.out.println(Thread.currentThread().getName() + "驶出停车场");
                    semaphore.release();//释放令牌，腾出停车场车位
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, UUID.randomUUID() + "号车");
            thread.start();
        }
    }

    @Test
    public void test1(){

    }
}
