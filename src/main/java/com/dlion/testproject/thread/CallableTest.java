package com.dlion.testproject.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lizy
 * @date 2021/7/9 15:25
 */


class Thread1 implements Runnable {
    @Override
    public void run() {
        System.out.println("runnable ...");
    }
}

class Thread2 implements Callable {

    @Override
    public Integer call() throws Exception {
        return 100;
    }
}


public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new Thread1(), "A").start();
        FutureTask<Integer> futureTask = new FutureTask<>(new Thread2());
//        FutureTask<Integer> futureTask2 = new FutureTask<>(()->{
//            return 100;
//        });
        new Thread(futureTask).start();
        while (!futureTask.isDone()){
            System.out.println(Thread.currentThread().getName() + "callable的执行结果：" +futureTask.get());
        }

    }
}

