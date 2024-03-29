package com.dlion.testproject;

import com.dlion.testproject.model.User;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.common.protocol.types.Field;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

import static com.sun.xml.internal.fastinfoset.util.ValueArray.MAXIMUM_CAPACITY;

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
    public void test() {
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

        Person() {
        }

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
    public void test2() {
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    @Test
    public void test3() {

        System.out.println(1 & 2);

        System.out.println(3 | 9);

        System.out.println(6 & 11);

        //ConcurrentHashMap

    }

    @Test
    public void test4() {
        String key = "ab";
        int h;
        int hashCode = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        System.out.println(hashCode);


        System.out.println(hashCode % 16);

        System.out.println(hashCode & 15);
    }

    @Test
    public void test5() {
        int cap = 9;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        int a = (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
        System.out.println(a);
    }

    @Test
    public void test6() {
        //奇数判断x & 1 = 1偶数判断x & 1 = 0
        System.out.println(13 & 1);
        System.out.println(12 & 1);
    }

    @Test
    public void test7() {
        //取模运算
        System.out.println(34 % 3);

        System.out.println(3 % 2 != 0);

        System.out.println(16 >>> 16);

        System.out.println(15 + (15 >> 1));
    }

    @Test
    public void test8() throws InterruptedException {
        Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        lock.lock();
        condition.await();
        condition.signal();
        lock.unlock();
        //HashMap
        //CountDownLatch
    }

    @Test
    public void test9() {
        String a = "0";
        String b = "a";
        System.out.println(Objects.hash(a));
        System.out.println(Objects.hash(b));

        System.out.println(Objects.hash(a) ^ Objects.hash(b));
    }

    public final int hashCode() {
        // return Objects.hashCode(key) ^ Objects.hashCode(value);
        return 0;
    }

    public static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Test
    public void test10() {
        int cap = 9;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        int a = (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
        System.out.println(a);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @Test
    public void test11() {
        Integer i3 = 128;
        Integer i4 = 128;
        // -128 --- 127
        System.out.println(i3 == i4);//输出false

        Integer.parseInt("");
    }

    @Test
    public void test12() {
        int a = 11;
        int b = 2;
        assert a == 10 : "a不等于10";
        System.out.println("a=" + a);
    }

    @Test
    public void test13() {

        Map map = new HashMap();
        Iterator iterator = Collections.emptyIterator();
        final Set set = map.keySet();
        List list = new ArrayList();
        final val iterator1 = list.iterator();
        iterator.next();
    }

    @Test
    public void test14() {
        System.out.println(4 % 2);
    }

    @Test
    public void test15() {
        int count = 0;
        int n = 100;
        int num = 1;
        while (num <= n) {
            num = 2 * num;
            count++;
        }
        System.out.println(count);
    }

    @Test
    public void test16() {
        int n = 100;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; i++) {
                // ...
            }
        }
    }

    @Test
    /**
     *  奇偶树判断
     */
    public void test17(){
        System.out.println(4 & 1);
        System.out.println(5 & 1);
    }

    @Test
    public void test18(){
        System.out.println(14>>>2);
    }

    @Test
    public void test19(){
        System.out.println("20211224104907831_1640314147831SCVCKM100".length());
    }

    @Test
    public void test20(){

        Map<String, Integer> count = new HashMap<>();
        Map<String, Object> params = new LinkedHashMap<>();

        String a = "1";
        String b = "2";
        String c = "3";
        User user = new User();
        user.setName("test");


        String[] paramTypes = {a.getClass().getName(), b.getClass().getName(), c.getClass().getName(), user.getClass().getName(), user.getClass().getName()};
        Object[] args = {"1", "2", "2", user, user};

        for (int i = 0; i < paramTypes.length; i++) {
            String type = paramTypes[i];
            Object val = args[i];
            int num;
            if(count.containsKey(type)){
                num = count.get(type);
                num ++;
            } else {
                num = 1;
            }
            count.put(type, num);
            type = type + "|" + num;
            params.put(type, val);
        }

        System.out.println(params);
    }

    @Test
    public void test21(){
        String a = "java.lang.String|1";

        a= StringUtils.substringBefore(a, "|");

        System.out.println(a);
    }


    @Test
    public void test22(){
        // 客户应该缴纳的
        double a = 1468.95 * 2 + 1468.95 / 30 * 6;
        // 客户总缴纳的房租
        double sum = 3936.9;
        System.out.println(sum - a);
    }

}
