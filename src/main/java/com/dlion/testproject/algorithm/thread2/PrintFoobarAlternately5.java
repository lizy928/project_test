package com.dlion.testproject.algorithm.thread2;

public class PrintFoobarAlternately5 {

    public static void main(String[] args) {
        FooBar8 fooBar = new FooBar8(2);//打印10次foo bar
        Runnable printFoo = () -> {
            System.out.printf("%s\n", "foo");
        };
        Runnable printBar = () -> {
            System.out.printf("%s\n", "bar");
        };
        Thread fooThread = new Thread(() -> {
            try {
                fooBar.foo(printFoo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread barThread = new Thread(() -> {
            try {
                fooBar.bar(printBar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        fooThread.start();
        barThread.start();
    }
}

class FooBar8 {
    private int n;

    private volatile boolean fooExec = true;

    private Object lock = new Object();

    public FooBar8(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                if (!fooExec) {//fooExec为false时，该线程等待，为true的时候执行下面的操作
                    lock.wait();
                }
                printFoo.run();
                fooExec = false;
                lock.notifyAll();//唤醒其他线程
            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                if (fooExec) {
                    lock.wait();
                }
                printBar.run();
                fooExec = true;
                lock.notifyAll();
            }
        }
    }

}
