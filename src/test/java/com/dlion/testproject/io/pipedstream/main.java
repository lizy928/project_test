package com.dlion.testproject.io.pipedstream;

import org.junit.Test;

import java.io.IOException;

/**
 *
 * 管道流主要用于两个线程间的通信，即一个线程通过管道流给另一个线程发数据
 *
 * 注意：线程的通信一般使用wait()/notify(),使用流也可以达到通信的效果，并且可以传递数据
 *
 * 使用的类是如下
 *
 * PipedInputStream和PipedOutStream
 * PipedReader和PipedWriter
 *
 * @author lzy
 * @date 2020/9/23
 */
public class main {

    @Test
    public void test() throws IOException {
        Sender sender = new Sender("hello");
        Receiver receiver = new Receiver();
        receiver.getPis().connect(sender.getPos());
        new Thread(sender).start();
        new Thread(receiver).start();
    }
}
