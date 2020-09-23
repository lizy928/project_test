package com.dlion.testproject.io.characterstream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;

/**
 *
 *
 * 缓冲流读取数据比普通流读取数据快很多！
 * 注意：采用边读边写的方式，一次传输几兆的数据效率比较高，如果采用先把文件的数据都读入内存，在进行写出，这样读写的次数是较小，但是占用太大的内存空间，一次读太大的数据也严重影响效率!
 *
 * @author lzy
 * @date 2020/9/23
 */
@Slf4j
public class main implements Serializable{

    /**
     * 使用基本的流读取数据(一次传输一个字节)
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("/Users/dliony/test/io/世界大战01.mp4");
        FileOutputStream fos = new FileOutputStream("/Users/dliony/test/io/世界大战01_拷贝.mp4");
        int data;
        while ((data = fis.read()) != -1) {
            fos.write(data);
        }
        log.info("拷贝电影耗时:{}ms", System.currentTimeMillis() - start);
        // 五分钟还没拷好，关闭程序了...
    }

    /**
     * 使用缓冲流读取数据(一次传输一个字节)
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("/Users/dliony/test/io/世界大战01.mp4");
        FileOutputStream fos = new FileOutputStream("/Users/dliony/test/io/世界大战01_拷贝.mp4");
        int len;
        byte[] data = new byte[1024 * 1024 * 1024];
        while ((len = fis.read(data)) != -1) {
            fos.write(data, 0, len);
        }
        log.info("拷贝电影耗时:{}ms", System.currentTimeMillis() - start);
        // 拷贝电影耗时:4651ms
    }

    /**
     * 使用缓冲流读取数据(一次传输一个8M的字节数组)(最常使用)
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        long start = System.currentTimeMillis();
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream("/Users/dliony/test/io/世界大战01.mp4"));
        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("/Users/dliony/test/io/世界大战01_拷贝.mp4"));

        int len;
        byte[] data = new byte[8 * 1024];
        while ((len = fis.read(data)) != -1) {
            fos.write(data, 0, len);
        }
        log.info("拷贝电影耗时:{}ms", System.currentTimeMillis() - start);
        // 拷贝电影耗时:1946ms
    }

    /**
     *
     */
    @Test
    public void test3(){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            long start = System.currentTimeMillis();
            bis = new BufferedInputStream(new FileInputStream(new File("/Users/dliony/test/io/世界大战01.mp4")));
            bos = new BufferedOutputStream(new FileOutputStream(new File("/Users/dliony/test/io/世界大战01_拷贝.mp4")));
            int len;
            // 一次传输8M的文件,实际测试这里传输的大小并不影响传输的速度
            byte[] data = new byte[8 * 1024];
            while ((len = bis.read(data)) != -1) {
                bos.write(data, 0, len);
            }
            log.info("拷贝电影耗时:{}ms", System.currentTimeMillis() - start);
        } catch (IOException e) {
            log.error("error", e);
        } finally {
            // finally块中关闭流，确保资源一定被关闭
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.error("error", e);
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    log.error("error", e);
                }
            }
        }
    }


}
