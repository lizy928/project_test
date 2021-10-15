package com.dlion.testproject.classinit;

public class Animal {

    /* 8、执行初始化 */
    private int i = 9;
    protected int j;

    /* 7、调用构造方法，创建默认属性和方法，完成后发现自己没有父类 */
    public Animal() {
        /* 9、执行构造方法剩下的内容，结束后回到子类构造函数中 */
        System.out.println("i = " + i + ", j = " + j);
        j = 39;
    }

    /* 2、初始化根基类的静态对象和静态方法 */
    private static int x1 = print("static Animal.x1 initialized");

    static int print(String s) {
        System.out.println(s);
        return 47;
    }

}
