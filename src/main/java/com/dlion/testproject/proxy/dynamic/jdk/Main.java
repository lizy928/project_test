package com.dlion.testproject.proxy.dynamic.jdk;

import com.dlion.testproject.proxy.staticproxy.SmsService;
import com.dlion.testproject.proxy.staticproxy.SmsServiceImpl;

/**
 * 在 Java 动态代理机制中 InvocationHandler 接口和 Proxy 类是核心。
 * Proxy 类中使用频率最高的方法是：newProxyInstance() ，这个方法主要用来生成一个代理对象
 * <p>
 * loader :类加载器，用于加载代理对象。
 * interfaces : 被代理类实现的一些接口；
 * h : 实现了 InvocationHandler 接口的对象；
 *
 * JDK 动态代理有一个最致命的问题是其只能代理实现了接口的类
 *
 * 1、代理类继承了Proxy类并且实现了要代理的接口，由于java不支持多继承，所以JDK动态代理不能代理类
 *
 * 2、重写了equals、hashCode、toString
 *
 * 3、有一个静态代码块，通过反射或者代理类的所有方法
 *
 * 4、通过invoke执行代理类中的目标方法doSomething
 *
 *
 * @author lzy
 * @date 2020/9/4
 */
public class Main {

    public static void main(String[] args) {
        // jdk动态代理测试
        SmsService smsService = new JDKDynamicProxy(new SmsServiceImpl()).getProxy();
        smsService.send("java");
    }
}
