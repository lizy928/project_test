package com.dlion.testproject;

import com.dlion.testproject.threadpool.ThreadPoolFactory;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.runAsync;

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
 * @date Created in 2022年02月24日 15:44
 * @since 1.0
 */
public class SynTest {

    public static void main(String[] args) {
        CompletableFuture<Void> test1 =
                runAsync(SynTest::test1, ThreadPoolFactory.getCachedThreadPool("test1"));

        CompletableFuture<Void> test2 =
                runAsync(SynTest::test2, ThreadPoolFactory.getCachedThreadPool("test2"));

        CompletableFuture<Object> test21 = CompletableFuture.supplyAsync(SynTest::test2, ThreadPoolFactory.getCachedThreadPool("test2"));


        test1.join();
        test2.join();

        try {
            Object re = test1.get();
            System.out.println(re);

            Object re2 = test1.get();
            System.out.println(re2);

            Object re3 = test21.get();
            System.out.println(re3);

        }catch (Exception e){

        }


    }

    public static Object test1(){
        return 1;
    }

    public static Object test2(){
        return 2;
    }
}
