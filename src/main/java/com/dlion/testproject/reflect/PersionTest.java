package com.dlion.testproject.reflect;

import java.lang.reflect.Method;

/**
 * @author 李正元
 * @date 2019-08-18
 */

public class PersionTest {

    public static void main(String[] args) throws Exception {

        Class<?> aClass = Class.forName("com.dlion.testproject.reflect.Person");

        Method[] methods = aClass.getMethods();

        for (int i = 0; i < methods.length; i++) {
            //methods[i].invoke(aClass);
        }


    }

}
