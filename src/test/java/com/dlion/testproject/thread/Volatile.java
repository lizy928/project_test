package com.dlion.testproject.thread;

/**
 * @author 李正元
 * @date 2019-08-31
 */

public class Volatile extends Thread {


    private boolean isRunning = true;


    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {

        System.out.println("run...");

        setRunning(false);

        while (isRunning == true) {
            System.out.println("whilt...");

        }
        System.out.println("stop...");

    }


}
