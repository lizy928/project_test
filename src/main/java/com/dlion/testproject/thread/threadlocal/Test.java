package com.dlion.testproject.thread.threadlocal;

import java.time.format.DateTimeFormatter;

public class Test {

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    static ThreadLocal<String> threadLocal2 = new ThreadLocal<>();

    public static void main(String[] args) {

        threadLocal.set("A");
        threadLocal.set("B");
        threadLocal.set("C");

        threadLocal2.set("D");
        threadLocal2.set("E");

        //DateTimeFormatter

        test();

    }

    public static void test(){
        String s = threadLocal.get();
        System.out.println(s);
    }
}
