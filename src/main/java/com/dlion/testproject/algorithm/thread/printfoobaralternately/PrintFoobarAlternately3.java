package com.dlion.testproject.algorithm.thread.printfoobaralternately;

import java.util.concurrent.locks.ReentrantLock;

public class PrintFoobarAlternately3 {

    public static void main(String[] args) {
        FooBar6 fooBar = new FooBar6(2);//打印10次foo bar
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

class FooBar6 {
    private int n;

    private boolean fooExec = true;

    private final ReentrantLock lock = new ReentrantLock(true);

    public FooBar6(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; ) {
            lock.lock();
            if(fooExec){
                printFoo.run();
                fooExec = false;
                i ++;
            }
            lock.unlock();
        }
    }
    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; ) {
            lock.lock();
            if(!fooExec){
                printBar.run();
                i ++;
                fooExec = true;
            }
            lock.unlock();
        }
    }

}
