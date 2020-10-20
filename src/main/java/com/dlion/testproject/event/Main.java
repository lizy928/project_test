package com.dlion.testproject.event;

/**
 *
 * 观察者模式是一种对象行为型模式。它表示的是一种对象与对象之间具有依赖关系，当一个对象发生改变的时候，这个对象所依赖的对象也会做出反应。
 * Spring 事件驱动模型就是观察者模式很经典的一个应用。Spring 事件驱动模型非常有用，在很多场景都可以解耦我们的代码。
 * 比如我们每次添加商品的时候都需要重新更新商品索引，这个时候就可以利用观察者模式来解决这个问题。
 *
 * Spring 事件驱动模型中的三种角色
 * 事件角色
 * ApplicationEvent (org.springframework.context包下)充当事件的角色,这是一个抽象类，它继承了java.util.EventObject并实现了 java.io.Serializable接口。
 *
 * Spring 中默认存在以下事件，他们都是对 ApplicationContextEvent 的实现(继承自ApplicationContextEvent)：
 *
 * ContextStartedEvent：ApplicationContext 启动后触发的事件;
 * ContextStoppedEvent：ApplicationContext 停止后触发的事件;
 * ContextRefreshedEvent：ApplicationContext 初始化或刷新完成后触发的事件;
 * ContextClosedEvent：ApplicationContext 关闭后触发的事件。
 *
 * ApplicationListener 充当了事件监听者角色，它是一个接口，里面只定义了一个 onApplicationEvent（）方法来处理ApplicationEvent。ApplicationListener接口类源码如下，
 * 可以看出接口定义看出接口中的事件只要实现了 ApplicationEvent就可以了。所以，在 Spring中我们只要实现 ApplicationListener 接口的 onApplicationEvent() 方法即可完成监听事件
 *
 * ApplicationEventPublisher 充当了事件的发布者，它也是一个接口。
 *
 * Spring 的事件流程总结
 * 定义一个事件: 实现一个继承自 ApplicationEvent，并且写相应的构造函数；
 * 定义一个事件监听者：实现 ApplicationListener 接口，重写 onApplicationEvent() 方法；
 * 使用事件发布者发布消息: 可以通过 ApplicationEventPublisher   的 publishEvent() 方法发布消息。
 *
 * @author lzy
 * @date 2020/10/19
 */
public class Main {

    public static void main(String[] args) {
        int[] test = test(new int[]{3, 2, 4}, 6);
        System.out.println(test.toString());
    }

    public static int[] test(int[] nums, int target){
        for(int i=0;i<nums.length;i++){
            int num = nums[i];
            for(int j=0;j<nums.length;j++){
                int num1 = nums[j];
                if(num + num1 == target){
                    return new int[] {i,j};
                }
            }
        }
        return new int[2];
    }
}
