package com.dlion.testproject.classinit;

public class Dog  extends Animal {

    /* 10、初始化默认的属性和方法 */
    private int k = print("Dog.k initialized");

    /*
     * 6、开始创建对象，即分配存储空间->创建默认的属性和方法。 遇到隐式或者显式写出的super()跳转到父类Animal的构造函数。
     * super()要写在构造函数第一行
     */
    public Dog() {
        /* 11、初始化结束执行剩下的语句 */
        System.out.println("k = " + k);
        System.out.println("j = " + j);
    }

    /* 3、初始化子类的静态对象静态方法，当然mian函数也是静态方法 */
    private static int x2 = print("static Dog.x2 initialized");

    /*
     * 1、要执行静态main，首先要加载Dog.class文件，加载过程中发现有父类Animal，
     * 所以也要加载Animal.class文件，直至找到根基类，这里就是Animal
     */
    public static void main(String[] args) {

        /* 4、前面步骤完成后执行main方法，输出语句 */
        System.out.println("Dog constructor");
        /* 5、遇到new Dog()，调用Dog对象的构造函数 */
        Dog dog = new Dog();
        /* 12、运行main函数余下的部分程序 */
        System.out.println("Main Left");
    }

}
