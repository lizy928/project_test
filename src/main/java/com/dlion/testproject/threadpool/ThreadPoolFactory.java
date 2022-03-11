package com.dlion.testproject.threadpool;

import org.apache.lucene.util.NamedThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
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
 * @date Created in 2022年02月24日 15:45
 * @since 1.0
 */
public class ThreadPoolFactory {

    private static volatile ThreadPoolExecutor threadPoolExecutor;
    private static final int corePoolSize = 80;
    private static final int maximumPoolSize = 250;
    private static final int keepAliveTime = 60000;

    private ThreadPoolFactory() {
    }

    public static ThreadPoolExecutor getCachedThreadPool(String threadName) {
        if (threadPoolExecutor == null) {
            Class var1 = ThreadPoolFactory.class;
            synchronized(ThreadPoolFactory.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new ThreadPoolExecutor(80, 250, 60000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new NamedThreadFactory(threadName), new ThreadPoolExecutor.DiscardPolicy());
                }
            }
        }

        return threadPoolExecutor;
    }
}
