package com.dlion.testproject;

/**
 * @author 李正元
 * @date 2019/8/28
 */
public class ThreadTest {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();



    }



}
