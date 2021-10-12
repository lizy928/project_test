package com.dlion.testproject.algorithm.thread.printfoobaralternately;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

public class PrintFoobarAlternately6 {

    public static void main(String[] args) {
        FooBar9 fooBar = new FooBar9(2);//打印10次foo bar
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

class FooBar9 {
    private int n;

    private volatile boolean fooExec = true;

    private Map<String, Thread> map = new ConcurrentHashMap<>();

    public FooBar9(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        map.put("foo", Thread.currentThread());
        for (int i = 0; i < n; i++) {
            while (!fooExec){
                LockSupport.park();
            }
            printFoo.run();
            fooExec = false;
            LockSupport.unpark(map.get("bar"));
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        map.put("bar", Thread.currentThread());
        for (int i = 0; i < n; i++) {
            while (fooExec){
                LockSupport.park();
            }
            printBar.run();
            fooExec = true;
            LockSupport.unpark(map.get("foo"));
        }
    }
}


