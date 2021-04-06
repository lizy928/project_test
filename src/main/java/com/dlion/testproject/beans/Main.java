package com.dlion.testproject.beans;

import org.junit.jupiter.api.Test;

/**
 * 实现*Aware接口 在Bean中使用Spring框架的一些对象
 * 有些时候我们需要在 Bean 的初始化中使用 Spring 框架自身的一些对象来执行一些操作，比如获取 ServletContext 的一些参数，
 * 获取 ApplicaitionContext 中的 BeanDefinition 的名字，获取 Bean 在容器中的名字等等。
 * 为了让 Bean 可以获取到框架自身的一些对象，Spring 提供了一组名为*Aware的接口。
 * <p>
 * <p>
 * ApplicationContextAware: 获得ApplicationContext对象,可以用来获取所有Bean definition的名字。
 * BeanFactoryAware:获得BeanFactory对象，可以用来检测Bean的作用域。
 * BeanNameAware:获得Bean在配置文件中定义的名字。
 * ResourceLoaderAware:获得ResourceLoader对象，可以获得classpath中某个文件。
 * ServletContextAware:在一个MVC应用中可以获取ServletContext对象，可以读取context中的参数。
 * ServletConfigAware： 在一个MVC应用中可以获取ServletConfig对象，可以读取config中的参数。
 *
 * @author lzy
 * @date 2020/10/19
 */
public class Main {

    @Test
    public void test(){

    }

}
