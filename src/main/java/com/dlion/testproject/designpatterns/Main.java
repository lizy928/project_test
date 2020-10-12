package com.dlion.testproject.designpatterns;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lzy
 * @date 2020/10/10
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(args);
        applicationContext.getBean("");
    }
}
