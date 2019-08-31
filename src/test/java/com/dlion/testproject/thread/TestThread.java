package com.dlion.testproject.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 李正元
 * @date 2019/8/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestThread {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ThreadFactoryService threadFactoryService;

    @Test
    public void test(){
        threadFactoryService.getExecutor(20000,"1001");
    }


}
