package com.dlion.testproject.interfacefunction;

/**
 * 使用实现类
 *
 * @author lzy
 * @date 2020/9/22
 */
public class demo_test {

    public static void main(String[] args) {

        //使用实现类
        //show(new Demo_FunctionalInterfaceImpl());
        Demo_FunctionalInterface demo = new Demo_FunctionalInterfaceImpl();
        demo.method();

        //使用匿名内部类
        Demo_FunctionalInterface demo1 = new Demo_FunctionalInterface() {
            @Override
            public void method() {
                System.out.println("handle method 111 使用匿名内部类");
            }
        };
        demo1.method();

        show(new Demo_FunctionalInterface() {
            @Override
            public void method() {
                System.out.println("handle method 222 使用匿名内部类");
            }
        });


        //使用lambda表达式
        Demo_FunctionalInterface demo2 = () -> System.out.println("handle method 111 使用lambda表达式");
        demo2.method();

        show(() -> System.out.println("handle method 222 使用lambda表达式"));

    }

    private static void show(Demo_FunctionalInterface e) {
        e.method();
    }

}
