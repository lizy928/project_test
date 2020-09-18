package com.dlion.testproject.proxy.dynamic.cglib;

import com.dlion.testproject.proxy.staticproxy.SmsServiceImpl;

/**
 * CGLIB(Code Generation Library)是一个基于ASM的字节码生成库，它允许我们在运行时对字节码进行修改和动态生成。CGLIB 通过继承方式实现代理。
 * 很多知名的开源框架都使用到了CGLIB， 例如 Spring 中的 AOP 模块中：如果目标对象实现了接口，则默认采用 JDK 动态代理，否则采用 CGLIB 动态代理
 * <p>
 * 在 CGLIB 动态代理机制中 MethodInterceptor 接口和 Enhancer 类是核心。
 * <p>
 * 定义一个类；
 * 自定义 MethodInterceptor 并重写 intercept 方法，intercept 用于拦截增强被代理类的方法，和 JDK 动态代理中的 invoke 方法类似；
 * 通过 Enhancer 类的 create()创建代理类；
 * <p>
 * <p>
 * <p>
 * JDK 动态代理只能只能代理实现了接口的类，而 CGLIB 可以代理未实现任何接口的类。 另外， CGLIB 动态代理是通过生成一个被代理类的子类来拦截被代理类的方法调用，
 * 因此不能代理声明为 final 类型的类和方法。
 * 就二者的效率来说，大部分情况都是 JDK 动态代理更优秀，随着 JDK 版本的升级，这个优势更加明显。
 * 4. 静态代理和动态代理的对比
 * 灵活性 ：动态代理更加灵活，不需要必须实现接口，可以直接代理实现类，并且可以不需要针对每个目标类都创建一个代理类。另外，静态代理中，接口一旦新增加方法，
 * 目标对象和代理对象都要进行修改，这是非常麻烦的！
 * JVM 层面 ：静态代理在编译时就将接口、实现类、代理类这些都变成了一个个实际的 class 文件。而动态代理是在运行时动态生成类字节码，并加载到 JVM 中的。
 *
 * @author lzy
 * @date 2020/9/4
 */
public class Main {

    public static void main(String[] args) {
        SmsServiceImpl aliSmsService = (SmsServiceImpl) CglibProxyFactory.getProxy(SmsServiceImpl.class);
        aliSmsService.send("java");
    }
}
