package com.dlion.testproject.blockingqueue;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
 * @date Created in 2022年03月04日 15:44
 * @since 1.0
 */
public class DelaySend {

    //推送队列
    static DelayQueue<DelayMessage> pushQueue = new DelayQueue<>();

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static ThreadFactory threadFactory = new DefaultThreadFactory("优惠活动标签推送搜索线程池");
    private static final ExecutorService executorService = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(200), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

    static {
        new Thread(()->{
            while (true) {
                DelayMessage msg;
                try {
                    msg = pushQueue.take();
                    executorService.submit(new MessageThread(msg));
                    long endTime = System.currentTimeMillis();
                    System.out.println(String.format("定时发送时间：%s,实际发送时间：%s,发送消息:%s",
                            timeInMillisToStr(msg.getSendTimeInMs()),
                            timeInMillisToStr(endTime),
                            msg));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        List<DelayMessage> messages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DelayMessage message = new DelayMessage();
            message.setModuleFid(String.valueOf(i));
            Thread.sleep(200);
            message.setSendTimeInMs(System.currentTimeMillis() + 5000);
            messages.add(message);
        }

        messages.parallelStream().forEach(DelaySend::add);

    }

    public static String timeInMillisToStr(long timeInMs) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeInMs), ZoneId.of("Asia/Shanghai"));
        return formatter.format(dateTime);
    }

    public static void add(DelayMessage message){
        System.out.println(String.format("时间:%s产生消息%s",
                timeInMillisToStr(System.currentTimeMillis()),
                message));
        pushQueue.put(message);
    }

    static class MessageThread extends Thread{

        private DelayMessage message;

        MessageThread(DelayMessage message){
            this.message = message;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(300);
                System.out.println("时间:" + timeInMillisToStr(System.currentTimeMillis()) + "开始推送：" + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
