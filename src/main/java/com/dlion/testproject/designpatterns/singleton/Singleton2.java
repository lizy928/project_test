package com.dlion.testproject.designpatterns.singleton;

/**
 * synchronized 关键字加到 static 静态方法和 synchronized(class)代码块上都是是给 Class 类上锁。
 * synchronized 关键字加到实例方法上是给对象实例上锁。尽量不要使用 synchronized(String a) 因为JVM中，字符串常量池具有缓存功能！
 * <p>
 * 双重校验锁实现对象单例（线程安全）
 *使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
 * @author lzy
 * @date 2020/9/12
 */
public class Singleton2 {

    private volatile static Singleton2 uniqueInstance;

    private Singleton2() {
    }

    public static Singleton2 getUniqueInstance() {
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) {
            //类对象加锁
            synchronized (Singleton2.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton2();
                }
            }
        }
        return uniqueInstance;
    }
}
