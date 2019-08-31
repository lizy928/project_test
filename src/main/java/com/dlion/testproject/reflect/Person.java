package com.dlion.testproject.reflect;

/**
 * @author 李正元
 * @date 2019-08-18
 */

public class Person {

    private String name;

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void eat() {

        System.out.println("eat...");
    }
}
