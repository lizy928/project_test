package com.dlion.testproject.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * 如果你要使用线程安全的集合的话， java.util.concurrent 包中提供了很多并发容器供你使用：
 * <p>
 * ConcurrentHashMap: 可以看作是线程安全的 HashMap
 * CopyOnWriteArrayList:可以看作是线程安全的 ArrayList，在读多写少的场合性能非常好，远远好于 Vector.
 * ConcurrentLinkedQueue:高效的并发队列，使用链表实现。可以看做一个线程安全的 LinkedList，这是一个非阻塞队列。
 * BlockingQueue: 这是一个接口，JDK 内部通过链表、数组等方式实现了这个接口。表示阻塞队列，非常适合用于作为数据共享的通道。
 * ConcurrentSkipListMap :跳表的实现。这是一个Map，使用跳表的数据结构进行快速查找
 *
 *
 *
 *
 *
 *
 *
 * @author lzy
 * @date 2020/9/4
 */
public class MapTest {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap(1);
        map.put(1, "Java");
        map.put(2, "C++");
        map.put(3, "PHP");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }
}
