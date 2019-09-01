package com.dlion.testproject.thread.Volatile;

/**
 * @author 李正元
 * @date 2019-08-31
 */

public class Run implements Runnable{

    public static void main(String[] args) {
        MyThread[] myThread = new MyThread[100];

        for(int i=0;i<100;i++){
            myThread[i] = new MyThread();
        }

        for(int i=0;i<100;i++){
            myThread[i].start();
        }

        boolean interrupted = Thread.interrupted();
        System.out.println(interrupted);


    }


    @Override
    public void run() {

    }



}
