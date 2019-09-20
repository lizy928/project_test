package com.dlion.testproject.thread.Volatile;

/**
 * @author 李正元
 * @date 2019-08-31
 */

public class MyThread extends Thread {

    volatile public static int count;

    synchronized private static void addCount(){

        for(int i=0;i<10;i++){
            count ++;
        }
        System.out.println("count:"+count);
    }

    @Override
    public void run(){
        addCount();
    }


}
