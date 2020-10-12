package com.dlion.testproject;

import org.junit.Test;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author lzy
 * @date 2020/10/10
 */
public class JavaTest {

    /**
     * 4      * 包含两个方法的HelloWorld接口
     * 5
     */
    interface HelloWorld {
        public void greet();

        public void greetSomeone(String someone);
    }

    /**
     * 匿名类实现HelloWorld接口
     */
    @Test
    public void test(){
        HelloWorld testHello = new HelloWorld() {
            private String str = "hello world";
            @Override
            public void greet() {
                this.greetSomeone(str);
            }

            @Override
            public void greetSomeone(String someone) {
                System.out.println(someone);
            }
        };
    }

    class Person {
        String firstName;
        String lastName;

        Person() {}

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }
    PersonFactory<Person> personFactory = Person::new;
    Person person = personFactory.create("Peter", "Parker");

    @Test
    public void test2(){
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

}
