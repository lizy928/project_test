package com.dlion.testproject.proxy.staticproxy;

/**
 * 使用代理对象来代替对真实对象(real object)的访问，这样就可以在不修改原目标对象的前提下，提供额外的功能操作，扩展目标对象的功能。
 * <p>
 * 静态代理实现步骤:
 * <p>
 * 定义一个接口及其实现类；
 * 创建一个代理类同样实现这个接口
 * 将目标对象注注入进代理类，然后在代理类的对应方法调用目标类中的对应方法。这样的话，我们就可以通过代理类屏蔽对目标对象的访问，并且可以在目标方法执行前后做一些自己想做的事情。
 *
 * @author lzy
 * @date 2020/9/4
 */
public class Main {

    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");
    }

}
