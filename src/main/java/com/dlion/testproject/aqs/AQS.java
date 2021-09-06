package com.dlion.testproject.aqs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lizy
 * @date 2021/7/9 11:18
 */
public class AQS {

    public static void main(String[] args) {
        AbstractQueuedSynchronizer abstractQueuedSynchronizer;
        ReentrantLock lock = new ReentrantLock();
        Map<String, Object> map = new HashMap<>();
        map.put("1", 1);
    }
}