package com.dlion.testproject.thread;

import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.SneakyThrows;
import org.apache.kafka.common.network.Send;
import org.apache.kafka.common.protocol.types.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author lizy
 * @version 1.0
 * @date Created in 2022年03月04日 12:11
 * @since 1.0
 */
public class SingleThread {

    ThreadFactory threadFactory = new DefaultThreadFactory("优惠活动标签推送搜索线程池");
    private final ExecutorService executorService = Executors.newSingleThreadExecutor(threadFactory);

    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        list.parallelStream().forEach(i -> {
            executorService.execute(new SendThread(i));
        });
    }

    static class SendThread extends Thread{

        private Integer id;

        SendThread(Integer id){
            this.id = id;
        }

        @SneakyThrows
        @Override
        public void run() {
            Thread.sleep(1000);
            System.out.println(this.id + "------>" + Thread.currentThread().getName());
        }
    }
}
