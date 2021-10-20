package com.dlion.testproject.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * Exchanger,俗称交换器,用于在线程之间交换数据,但是比较受限,因为只能两个线程之间交换数据
 *
 * @author lizy
 */
public class ExchangerDemo {

    private final static Exchanger<Set<String>> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            Set<String> data = new HashSet<>();
            data.add("1");
            data.add("2");
            data.add("3");
            try {
                Set<String> set = exchanger.exchange(data);
                for (String s : set) {
                    System.out.println(Thread.currentThread().getName() + "::" + s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AA").start();

        new Thread(() -> {
            Set<String> data = new HashSet<>();
            data.add("4");
            data.add("5");
            data.add("6");
            try {
                Set<String> set = exchanger.exchange(data);
                for (String s : set) {
                    System.out.println(Thread.currentThread().getName() + "::" + s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
