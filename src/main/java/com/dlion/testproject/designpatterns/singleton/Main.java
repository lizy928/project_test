package com.dlion.testproject.designpatterns.singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lzy
 * @date 2020/9/5
 */
public class Main {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                10L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20),
                new ThreadPoolExecutor.CallerRunsPolicy());

    }
}
