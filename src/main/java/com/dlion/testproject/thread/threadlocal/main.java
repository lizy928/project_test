package com.dlion.testproject.thread.threadlocal;

/**
 * @author lzy
 * @date 2020/9/23
 */
public class main {

    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            int num = HASH_INCREMENT & (i - 1);
            System.out.println("第" + i + "个在桶中的位置：" + num);
        }

    }
}
