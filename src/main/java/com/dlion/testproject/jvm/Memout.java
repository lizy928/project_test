package com.dlion.testproject.jvm;

import com.dlion.testproject.reflect.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李正元
 * @date 2019-08-18
 */

public class Memout {

    public static void main(String[] args) throws InterruptedException {

        add();

        Thread.sleep(100000);

    }


    private static void add() throws InterruptedException {

        List list = new ArrayList();

        for (int i = 0; i < 10000; i++) {
            Thread.sleep(10000);

            list.add(new Person());

            System.out.println(i);
        }




    }

}
