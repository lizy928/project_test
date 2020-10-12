package com.dlion.testproject.java8;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java.util.Stream 表示能应用在一组元素上一次执行的操作序列。
 * Stream 操作分为中间操作或者最终操作两种，最终操作返回一特定类型的计算结果，而中间操作返回Stream本身，这样你就可以将多个操作依次串起来。
 * Stream 的创建需要指定一个数据源，比如 java.util.Collection 的子类，List 或者 Set， Map 不支持。Stream 的操作可以串行执行或者并行执行。
 *
 * @author lzy
 * @date 2020/10/10
 */
public class StreamTest {

    private static final ArrayList<String> stringList;

    static {
        stringList = new ArrayList<>();
        stringList.add("ddd2");
        stringList.add("aaa2");
        stringList.add("bbb1");
        stringList.add("aaa1");
        stringList.add("bbb3");
        stringList.add("ccc");
        stringList.add("bbb2");
        stringList.add("ddd1");
    }

    /**
     * 测试 Filter(过滤)
     */
    @Test
    public void testFilter() {
        stringList
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
    }

    /**
     * 排序是一个 中间操作，返回的是排序好后的 Stream。如果你不指定一个自定义的 Comparator 则会使用默认排序。
     */
    @Test
    public void testSorted() {
        stringList
                .stream()
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * 中间操作 map 会将元素根据指定的 Function 接口来依次将元素转成另外的对象。
     * 你也可以通过map来将对象转换成其他类型，map返回的Stream类型是根据你map传递进去的函数的返回值决定的。
     */
    @Test
    public void testMap() {
        stringList
                .stream()
                .sorted()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    /**
     * Stream提供了多种匹配操作，允许检测指定的Predicate是否匹配整个Stream。所有的匹配操作都是 最终操作 ，并返回一个 boolean 类型的值。
     */
    @Test
    public void testMatch() {
        boolean anyStartsWithA =
                stringList
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA);

        boolean allStartsWithA =
                stringList
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));
        System.out.println(allStartsWithA);

        boolean noneStartsWithZ =
                stringList
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);
    }

    /**
     * 计数是一个 最终操作，返回Stream中元素的个数，返回值类型是 long。
     */
    @Test
    public void testCount() {
        Long count = stringList
                .stream()
                .filter((s) -> s.startsWith("a"))
                .count();
        System.out.println(count);
    }

    /**
     * 这是一个 最终操作 ，允许通过指定的函数来讲stream中的多个元素规约为一个元素，规约后的结果是通过Optional 接口表示的：
     */
    @Test
    public void testReduce() {
        Optional<String> reduced = stringList
                .stream()
                .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);

        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);
    }

    /**
     * 有时候我们会遇到提取子流的操作，这种情况用的不多但是遇到flatMap将变得更容易处理。
     */
    @Test
    public void testFlatMap() {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Arrays.asList("apple", "click"));
        lists.add(Arrays.asList("boss", "dig", "qq", "vivo"));
        lists.add(Arrays.asList("c#", "biezhi"));
        //要做的操作是获取这些数据中长度大于2的单词个数
        lists.stream()
                .flatMap(Collection::stream)
                .filter(str -> str.length() > 2)
                .count();
    }

    @Test
    public void test() {
        Property p1 = new Property("叫了个鸡", 1000, 500, 2);
        Property p2 = new Property("张三丰饺子馆", 2300, 1500, 3);
        Property p3 = new Property("永和大王", 580, 3000, 1);
        Property p4 = new Property("肯德基", 6000, 200, 4);
        List<Property> properties = Arrays.asList(p1, p2, p3, p4);

        //获取距离我最近的2个店铺
        List<Property> collect = properties.stream()
                .sorted(Comparator.comparingInt(x -> x.distance))
                .limit(2)
                .collect(Collectors.toList());

        //获取所有店铺的名称
        List<String> names = properties.stream()
                .map(p -> p.name)
                .collect(Collectors.toList());

        //获取每个店铺的价格等级
        Map<String, Integer> map = properties.stream()
                .collect(Collectors.toMap(Property::getName, Property::getPriceLevel));

        //所有价格等级的店铺列表
        Map<Integer, List<Property>> priceMap = properties.stream()
                .collect(Collectors.groupingBy(Property::getPriceLevel));
    }

    // 店铺属性
    @Data
    class Property {
        String name;
        // 距离，单位:米
        Integer distance;
        // 销量，月售
        Integer sales;
        // 价格，这里简单起见就写一个级别代表价格段
        Integer priceLevel;

        public Property(String name, int distance, int sales, int priceLevel) {
            this.name = name;
            this.distance = distance;
            this.sales = sales;
            this.priceLevel = priceLevel;
        }
        // getter setter 省略
    }

}
