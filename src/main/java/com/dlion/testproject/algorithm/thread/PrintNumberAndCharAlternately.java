package com.dlion.testproject.algorithm.thread;

/**
 * 写两个线程，一个线程打印1~52，另一个线程打印A~Z，打印顺序是12A34B...5152Z
 * @author dliony
 */
public class PrintNumberAndCharAlternately {

    public static void main(String[] args) {
        Print print = new Print();
        for (int i = 0; i < 26; i++) {
            new Thread(print::printNumber).start();
        }

        for (int i = 0; i < 26; i++) {
            new Thread(print::printChar).start();
        }
    }
}

class Print {
    private int flag = 1;//信号量！！当值为1时打印数字，当值为2时打印字母
    private int count = 1; //12 34 56 78 910

    public synchronized void printNumber() {
        while (flag != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(count * 2 - 1);
        System.out.println(count * 2);
        flag = 2;
        notifyAll();
    }

    public synchronized void printChar() {
        while (flag == 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println((char) (count - 1 + 'A'));
        count ++;
        flag = 1;
        notifyAll();
    }
}
