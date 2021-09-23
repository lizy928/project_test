package com.dlion.testproject.thread.communication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 第二种解法，是利用CyclicBarrierAPI；
 * <p>
 * 这个API其实还是利用lock和condition，无非是多个线程去争抢CyclicBarrier的instance的lock罢了，
 * 最终barrierAction执行时，是在抢到CyclicBarrierinstance的那个线程上执行的。
 *
 * @author lizy
 * @date 2021/9/9 10:42
 */
public class MethodFive {

    private final CyclicBarrier barrier;

    private final List<String> list;

    public MethodFive() {
        list = Collections.synchronizedList(new ArrayList<String>());
        barrier = new CyclicBarrier(2, newBarrierAction());
    }

    public Runnable newThreadOne() {
        final String[] inputArr = Helper.buildNoArr(52);
        return new Runnable() {
            private String[] arr = inputArr;

            public void run() {
                for (int i = 0, j = 0; i < arr.length; i = i + 2, j++) {
                    try {
                        list.add(arr[i]);
                        list.add(arr[i + 1]);
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public Runnable newThreadTwo() {
        final String[] inputArr = Helper.buildCharArr(26);
        return new Runnable() {
            private String[] arr = inputArr;

            public void run() {
                for (int i = 0; i < arr.length; i++) {
                    try {
                        list.add(arr[i]);
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    private Runnable newBarrierAction() {
        return new Runnable() {
            @Override
            public void run() {
                Collections.sort(list);
                list.forEach(c -> System.out.print(c));
                list.clear();
            }
        };
    }

    public static void main(String args[]) {
        MethodFive four = new MethodFive();
        Helper.instance.run(four.newThreadOne());
        Helper.instance.run(four.newThreadTwo());
        Helper.instance.shutdown();

    }
}