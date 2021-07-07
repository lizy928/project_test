package com.dlion.testproject.aqs;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    /**
     * 使用 ReentrantReadWriteLock 来提高 Collection 的并发性
     * 通常在 collection 数据很多，读线程访问多于写线程并且 entail 操作的开销高于同步开销时尝试这么做。
     */
    Map<String, Integer> collection = new TreeMap();
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public Integer getData(String key) {
        readLock.lock();
        try {
            return collection.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Integer putData(String key, Integer data) {
        writeLock.lock();
        try {
            return collection.put(key, data);
        } finally {
            writeLock.unlock();
        }
    }


    Object data;
    volatile boolean cacheValid;    //缓存是否有效
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    /**
     * 利用重入来执行升级缓存后的锁降级
     */
    @Test
    public void test() {
        rwl.readLock().lock();    //获取读锁
        //如果缓存无效，更新cache;否则直接使用data
        if (!cacheValid) {
            // Must release read lock before acquiring write lock
            //获取写锁前须释放读锁
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            // Recheck state because another thread might have acquired
            //   write lock and changed state before we did.
            if (!cacheValid) {
                data = 1;
                cacheValid = true;
            }
            // Downgrade by acquiring read lock before releasing write lock
            //锁降级，在释放写锁前获取读锁
            rwl.readLock().lock();
            rwl.writeLock().unlock(); // Unlock write, still hold read
        }
        System.out.println(data);
        rwl.readLock().unlock();    //释放读锁
    }


}
