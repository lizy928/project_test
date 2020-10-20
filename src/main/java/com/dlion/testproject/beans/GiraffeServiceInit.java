package com.dlion.testproject.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * initialization 和 destroy的两种方案：
 * 1.实现InitializingBean和DisposableBean接口
 * 这两个接口都只包含一个方法。通过实现InitializingBean接口的afterPropertiesSet()方法可以在Bean属性值设置好之后做一些操作，实现DisposableBean接口的destroy()方法可以在销毁Bean之前做一些操作。
 * 2.在bean的配置文件中指定init-method和destroy-method方法
 * Spring允许我们创建自己的 init 方法和 destroy 方法，只要在 Bean 的配置文件中指定 init-method 和 destroy-method 的值就可以在 Bean 初始化时和销毁之前执行一些操作。
 *
 * @author lzy
 * @date 2020/10/19
 */
public class GiraffeServiceInit implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() {
        System.out.println("执行InitializingBean接口的afterPropertiesSet方法");
    }

    @Override
    public void destroy() {
        System.out.println("执行DisposableBean接口的destroy方法");
    }
}
