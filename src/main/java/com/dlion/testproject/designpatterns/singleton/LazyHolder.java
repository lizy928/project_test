package com.dlion.testproject.designpatterns.singleton;

/**
 * 懒汉式单例模式，在第一次调用时实例化
 *
 * @author lzy
 * @date 2020/9/4
 */
public class LazyHolder {

    public LazyHolder() {
    }

    private static LazyHolder lazyHolder = null;

    /**
     * //方式一
     * //非线程安全，加上同步锁，但是每次都要同步，会影响性能
     *
     * @return
     */
    public static synchronized LazyHolder getLazyHolder() {
        if (lazyHolder == null) {
            lazyHolder = new LazyHolder();
        }
        return lazyHolder;
    }

    /**
     * //方式二
     * //做了两次null检查，确保了只有第一次调用单例的时候才会做同步，这样也是线程安全的，同时避免了每次都同步的性能损耗
     *
     * @return
     */
    public static LazyHolder getLazyHolder2() {
        if (lazyHolder == null) {
            synchronized (LazyHolder.class) {
                if (lazyHolder == null) {
                    lazyHolder = new LazyHolder();
                }
            }
        }
        return lazyHolder;
    }


    static class LazyHolder2 {


    }


    public static void main(String[] args) {
        getLazyHolder();
    }

    /**
     * //饿汉式单例
     * //饿汉式在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，线程安全
     */
    static class Singleton {
        private Singleton() {
        }

        private static final Singleton singleton = new Singleton();

        //静态工厂方法
        public static Singleton getInstance() {
            return singleton;
        }
    }

}
