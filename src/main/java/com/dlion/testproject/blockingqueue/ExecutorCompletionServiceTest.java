package com.dlion.testproject.blockingqueue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

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
 * @date Created in 2022年03月01日 15:16
 * @since 1.0
 */
public class ExecutorCompletionServiceTest {

    //推送队列
    static DelayQueue<Message> pushQueue = new DelayQueue<Message>();

    //推送信息封装
    static class Message implements Delayed {
        //优先级，越小优先级越高
        private int priority;
        private String msg;
        private long sendTimeInMs;

        public Message(int priority, String msg, long sendTimeInMs) {
            this.priority = priority;
            this.msg = msg;
            this.sendTimeInMs = sendTimeInMs;
        }

        @Override
        public String toString() {
            return String.format("消息内容[priority=%s,msg=%s,sendTime=%s]", priority, msg, timeInMillisToStr(sendTimeInMs));
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.sendTimeInMs - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (o instanceof Message) {
                Message c2 = (Message) o;
                return Integer.compare(this.priority, c2.priority);
            }
            return 0;
        }
    }

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String timeInMillisToStr(long timeInMs) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeInMs), ZoneId.of("Asia/Shanghai"));
        return formatter.format(dateTime);
    }

    static {
        //启动一个线程做真实推送
        new Thread(() -> {
            while (true) {
                Message msg;
                try {
                    msg = pushQueue.take();
                    long endTime = System.currentTimeMillis();
                    System.out.println(String.format("定时发送时间：%s,实际发送时间：%s,发送消息:%s",
                            timeInMillisToStr(msg.sendTimeInMs),
                            timeInMillisToStr(endTime),
                            msg));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //推送消息，需要发送推送消息的调用该方法，会将推送信息先加入推送队列
    public static void pushMsg(int priority, String msg, long sendTimeMs) throws InterruptedException {
        Message message = new Message(priority, msg, sendTimeMs);
        System.out.println(String.format("时间:%s产生消息%s",
                timeInMillisToStr(System.currentTimeMillis()),
                message));
        pushQueue.put(message);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 5; i >= 1; i--) {
            String msg = "延迟发送消息示例,延迟" + i * 2000 + "ms发送";
            pushMsg(i, msg, System.currentTimeMillis() + i * 2000);
        }
    }

}
