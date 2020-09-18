package com.dlion.testproject.designpatterns.singleton;

/**
 * 方式三
 * 实现了线程安全，同事避免了同步带来的性能影响
 *
 * @author lzy
 * @date 2020/9/4
 */
public class LazyHolder2 {

    private static class Singleton {
        private static final LazyHolder.LazyHolder2 INSTANT = new LazyHolder.LazyHolder2();
    }

    private LazyHolder2() {
    }

    public static final LazyHolder.LazyHolder2 getLazy() {
        return Singleton.INSTANT;
    }
}
