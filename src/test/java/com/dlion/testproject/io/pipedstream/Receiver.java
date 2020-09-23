package com.dlion.testproject.io.pipedstream;

import lombok.SneakyThrows;

import java.io.PipedInputStream;

/**
 * @author lzy
 * @date 2020/9/23
 */
public class Receiver implements Runnable{

    private PipedInputStream pis;

    public Receiver() {
        this.pis = new PipedInputStream();
    }

    public PipedInputStream getPis() {
        return pis;
    }

    @SneakyThrows
    @Override
    public void run() {
        byte[] data = new byte[1024];
        int len;
        while ((len = pis.read(data)) != -1) {
            System.out.println(new String(data, 0, len));
        }
    }
}
