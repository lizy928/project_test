package com.dlion.testproject.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Optional 类主要解决的问题是臭名昭著的空指针异常
 *
 * @author lzy
 * @date 2020/10/13
 */
@Slf4j
public class OptionalTest {

    /**
     * 你应该明确对象不为 null  的时候使用 of()。
     * 如果对象即可能是 null 也可能是非 null，你就应该使用 ofNullable() 方法：
     */
    @Test
    public void test() {
        Optional<User> emptyOpt = Optional.empty();
        //System.out.println(emptyOpt.isPresent());
        //System.out.println(emptyOpt.get());

        User user = null;
        // null 值作为参数传递进去，of() 方法会抛出 NullPointerException
        Optional<User> opt = Optional.of(user);
        Optional<User> opt2 = Optional.ofNullable(user);
    }

    /**
     * 访问 Optional 对象的值
     * <p>
     * 从 Optional 实例中取回实际值对象的方法之一是使用 get() 方法：
     */
    @Test
    public void whenCreateOfNullableOptional_thenOk() {
        String name = "John";
        Optional<String> opt = Optional.ofNullable(name);
        assertEquals("John", opt.get());
    }

    /**
     * 这个方法会在值为 null 的时候抛出异常。要避免异常，你可以选择首先验证是否有值：
     */
    @Test
    public void whenCheckIfPresent_thenOk() {
        User user = new User("john@gmail.com", "1234");
        Optional<User> opt = Optional.ofNullable(user);
        assertTrue(opt.isPresent());
        assertEquals(user.getEmail(), opt.get().getEmail());
        //检查是否有值的另一个选择是 ifPresent() 方法。该方法除了执行检查，还接受一个Consumer(消费者) 参数，如果对象不是空的，就对执行传入的 Lambda 表达式：
        opt.ifPresent(u -> assertEquals(user.getEmail(), u.getEmail()));
    }

    /**
     * 返回默认值
     * Optional 类提供了 API 用以返回对象值，或者在对象为空的时候返回默认值。
     * <p>
     * 这里你可以使用的第一个方法是 orElse()，它的工作方式非常直接，如果有值则返回该值，否则返回传递给它的参数值：
     * <p>
     * 如果对象的初始值不是 null，那么默认值会被忽略：
     */
    @Test
    public void whenEmptyValue_thenReturnDefault() {
        User user = null;
        User user2 = new User("anna@gmail.com", "1234");
        User result = Optional.ofNullable(user).orElse(user2);
        assertEquals(user2.getEmail(), result.getEmail());

        //第二个同类型的 API 是 orElseGet() —— 其行为略有不同。这个方法会在有值的时候返回值，如果没有值，它会执行作为参数传入的 Supplier(供应者) 函数式接口，并将返回其执行结果：
        User result1 = Optional.ofNullable(user).orElseGet(() -> user2);
//        Supplier
//        Consumer
//        Function;
//        Predicate
    }

    /**
     * orElse() 和 orElseGet() 的不同之处
     * 乍一看，这两种方法似乎起着同样的作用。然而事实并非如此。我们创建一些示例来突出二者行为上的异同。
     * 我们先来看看对象为空时他们的行为：
     */
    @Test
    public void givenEmptyValueWhenCompareThenOk() {
        User user = null;
        log.debug("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        log.debug("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }

    /**
     * 我们接下来看一个类似的示例，但这里 Optional  不为空：
     */
    @Test
    public void givenPresentValue_whenCompare_thenOk() {
        User user = new User("john@gmail.com", "1234");
        log.info("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        log.info("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }

    /**
     * 返回异常
     * 除了 orElse() 和 orElseGet() 方法，Optional 还定义了 orElseThrow() API —— 它会在对象为空的时候抛出异常，而不是返回备选的值：
     */
    @Test
    public void whenThrowExceptionThenOk() {
        User user = null;
        User result = Optional.ofNullable(user)
                .orElseThrow(IllegalArgumentException::new);
    }

    /**
     * 转换值
     * 有很多种方法可以转换 Optional  的值。我们从 map() 和 flatMap() 方法开始。
     * 使用 map() API
     */
    @Test
    public void whenMapThenOk() {
        User user = new User("anna@gmail.com", "1234");
        String email = Optional.ofNullable(user)
                .map(u -> u.getEmail()).orElse("default@gmail.com");
        assertEquals(email, user.getEmail());
    }

    /**
     * 过滤值
     * <p>
     * 除了转换值之外，Optional  类也提供了按条件“过滤”值的方法。
     * filter() 接受一个 Predicate 参数，返回测试结果为 true 的值。如果测试结果为 false，会返回一个空的 Optional。
     * 来看一个根据基本的电子邮箱验证来决定接受或拒绝 User(用户) 的示例：
     */
    @Test
    public void whenFilter_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        Optional<User> result = Optional.ofNullable(user)
                .filter(u -> u.getEmail() != null && u.getEmail().contains("@"));
        assertTrue(result.isPresent());
    }

    @Test
    public void whenChainingThenOk() {
        User user = new User("anna@gmail.com", "1234");
        user.setAddress(new Address(new Country("123445")));

        String result = Optional.ofNullable(user)
                .flatMap(u -> u.getAddress())
                .flatMap(a -> a.getCountry())
                .map(c -> c.getIsocode())
                .orElse("default");

        assertEquals(result, "default");

        String result1 = Optional.ofNullable(user)
                .flatMap(User::getAddress)
                .flatMap(Address::getCountry)
                .map(Country::getIsocode)
                .orElse("default");

        System.out.println(result + ":::" + result1);
    }









    private User createNewUser() {
        log.debug("Creating New User");
        return new User("extra@gmail.com", "1234");
    }


    @Data
    @AllArgsConstructor
    static
    class Address {
        private Country country;

        public Optional<Country> getCountry() {
            return Optional.ofNullable(country);
        }
    }

    @Data
    @AllArgsConstructor
    static
    class Country {
        private String isocode;
    }

    @Data
    @AllArgsConstructor
    static
    class User {
        private String email;
        private String username;
        private Address address;

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        User(String email, String username) {
            this.email = email;
            this.username = username;
        }

    }

}
