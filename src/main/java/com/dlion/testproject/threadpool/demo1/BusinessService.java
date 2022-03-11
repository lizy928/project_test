package com.dlion.testproject.threadpool.demo1;

import com.dlion.testproject.threadpool.ThreadPoolFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
 * @date Created in 2022年03月11日 17:20
 * @since 1.0
 */
@Slf4j
public class BusinessService {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int roomId = 10000;

        Future<String> future = asyncSaveBusiness(roomId);
        System.out.println("get future ...");
        Runnable runnable = () -> {
            Object o = null;
            try {
                o = future.get();
            } catch (Exception e) {
                log.error("获取异步创建商机结果异常{}", e.getMessage());
            }
            System.out.println(o);
            System.out.println(future.isDone());
        };

        Executors.newSingleThreadExecutor().submit(runnable);

        System.out.println("返回结果");
    }

    public static Future<String> asyncSaveBusiness(int roomId){

        String id = "10";
        Runnable runnable = ()->{
            try {
                Thread.sleep(1000);
                log.info("保存商机成功。。。。");
            } catch (InterruptedException e) {
                log.error("异步保存商机异常: {}", e.getMessage());
            }
        };

        return ThreadPoolFactory.getCachedThreadPool("save_business").submit(runnable, id);
    }
}
