package com.dlion.testproject.io.pipedstream;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * @author lzy
 * @date 2020/9/23
 */
public class Sender implements Runnable {
    private PipedOutputStream pos;
    private String msg;

    public Sender(String msg) {
        this.pos = new PipedOutputStream();
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            pos.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PipedOutputStream getPos() {
        return pos;
    }
}
