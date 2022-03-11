package com.dlion.testproject.threadpool.demo1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
 * @date Created in 2022年03月11日 17:22
 * @since 1.0
 */
@Slf4j
public class Test {

    public static void main(String[] args) {

        Runnable runnable = () -> System.out.println("...");

        Future<?> submit = Executors.newSingleThreadExecutor().submit(runnable);

        try{
            submit.get(1000, TimeUnit.MINUTES);
            boolean done = submit.isDone();
            if(done){
                submit.cancel(true);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public static void test(){


    }
}
