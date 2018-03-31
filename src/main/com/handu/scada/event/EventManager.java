package main.com.handu.scada.event;


import main.com.handu.scada.MainServer;
import main.com.handu.scada.event.events.BaseEvent;
import main.com.handu.scada.event.publish.IPublisher;
import main.com.handu.scada.event.publish.PublisherImp;
import main.com.handu.scada.event.subscribe.ISubscriber;
import main.com.handu.scada.event.subscribe.SubscribePublish;
import main.com.handu.scada.netty.client.dtu.MsgPriority;
import main.com.handu.scada.utils.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * Created by 柳梦 on 2017/12/11.
 */
public class EventManager {

    private static EventManager singleton;
    private SubscribePublish subscribePublish;
    private IPublisher publisher;

    private EventManager() {
        subscribePublish = new SubscribePublish();
        publisher = new PublisherImp();
    }

    public static EventManager getInstance() {
        if (singleton == null) {
            synchronized (EventManager.class) {
                if (singleton == null) {
                    singleton = new EventManager();
                }
            }
        }
        return singleton;
    }

    /**
     * @param event
     */
    public void publish(BaseEvent event) {
        publish(event, false, MsgPriority.NORMAL);
    }

    /**
     * @param event
     * @param priority
     */
    public void publish(BaseEvent event, MsgPriority priority) {
        publish(event, false, priority);
    }

    /**
     * @param event
     * @param isInstantSend
     * @param priority
     */
    public void publish(BaseEvent event, boolean isInstantSend, MsgPriority priority) {
        publisher.publish(subscribePublish, event, isInstantSend, priority);
    }

    /**
     * @param iSubscriber
     */
    public void register(ISubscriber iSubscriber) {
        if (subscribePublish != null)
            iSubscriber.subscribe(subscribePublish);
    }

    public void unRegister() {
        if (subscribePublish != null) {
            subscribePublish.unAllSubscribe();
        }
    }

    /**
     * 初始化项目下所有的订阅者
     */
    public void injectSubscriber() {
        // 包下面的类
        Set<Class<?>> clazzs = AnnotationUtils.getClasses(MainServer.class.getPackage().getName());
        if (clazzs != null && clazzs.size() > 0) {
            for (Class<?> clazz : clazzs) {
                // 获取类上的注解
                Annotation[] annos = clazz.getAnnotations();
                for (Annotation anno : annos) {
                    //如果该对象是我们需要的注解类型
                    if (anno.annotationType() == Subscriber.class) {
                        try {
                            Constructor[] constructors = clazz.getDeclaredConstructors();
                            AccessibleObject.setAccessible(constructors, true);
                            //找到私有的构造函数
                            for (Constructor con : constructors) {
                                if (con.isAccessible()) {
                                    //调用构造函数获取订阅者实例
                                    ISubscriber iSubscriber = (ISubscriber) con.newInstance();
                                    //加入到订阅者列表
                                    register(iSubscriber);
                                }
                            }
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ignored) {
                        }
                    }
                }
            }
        }
    }
}
