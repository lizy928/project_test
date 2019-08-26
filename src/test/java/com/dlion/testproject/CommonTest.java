package com.dlion.testproject;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李正元
 * @date 2019/8/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTest {

    @Test
    public void test(){

       String imgPath = "http://cms-bucket.ws.126.net/2019/08/26/f8c9a2fcd45f4a8cb24c7bc43c4ac4a7.jpeg";
        StringUtils.substringBeforeLast(imgPath, ".");
    }

}
