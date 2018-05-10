package main.com.handu.scada.event.subscribe;

import main.com.handu.scada.event.events.BaseEvent;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.client.dtu.MsgPriority;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 订阅器类
 * Created by 柳梦 on 2017/12/11.
 */
public class SubscribePublish {

    /**
     * offer(E e):将给定的元素设置到队列中，如果设置成功返回true,否则返回false.e的值不能为空，否则抛出空指针异常。
     * <p>
     * offer(E e, long timeout, TimeUnit unit):将给定元素在给定的时间内设置到队列中，如果设置成功返回true,否则返回false。
     * <p>
     * add(E e):将给定元素设置到队列中，如果设置成功返回true,否则抛出异常。如果是往限定了长度的队列中设置值，
     * <p>
     * 推荐使用offer()方法。
     * <p>
     * put(E e):将元素设置到队列中，如果队列中没有多余的空间，该方法会一直阻塞，直到队列中有多余的空间。
     * <p>
     * take():从队列中获取值，如果队列中没有值，线程会一直阻塞，直到队列中有值，并且该方法取得了该值。
     * <p>
     * poll(long timeout, TimeUnit unit):在给定的时间里，从队列中获取值，如果没有取到会抛出异常。
     * <p>
     * remainingCapacity()：获取队列中剩余的空间。
     * <p>
     * remove(Object o):从队列中移除指定的值。
     * <p>
     * contains(Object o):判断队列中是否拥有该值。
     * <p>
     * drainTo(Collection c):将队列中值，全部移除，并发设置到给定的集合中。
     */

    //订阅器存储队列，采用优先级队列，优先级越高则先处理
    private BlockingQueue<Msg> queue = new PriorityBlockingQueue<>();
    //订阅者
    private Set<ISubscriber> subscribers = new LinkedHashSet<>();
    //线程池
    private ExecutorService service = Executors.newSingleThreadExecutor();

    /**
     * @Description:构造方法
     */
    public SubscribePublish() {
        loop();
    }

    /**
     * @param publisher
     * @param isInstantSend 是否立即发送
     * @param priority      优先级
     * @Description: 接收发布者的消息
     */
    public void publish(String publisher, BaseEvent message, boolean isInstantSend, MsgPriority priority) {
        if (isInstantSend) {
            expend(publisher, message);
            return;
        }
        //添加进队列
        try {
            Msg m = new Msg(publisher, message, priority);
            queue.put(m);
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * @param subscriber
     * @Description: 订阅
     * @return: void
     */
    public void subscribe(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * @param subscriber
     * @Description: 退订
     * @return: void
     */
    public void unSubscribe(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void unAllSubscribe() {
        subscribers.clear();
    }

    /**
     * @Description: 开始循环发送存储队列所有消息
     * @return: void
     */
    private void loop() {
        //循环拿出找到消费者
        service.execute(new LoopRunnable());
    }

    /**
     * @param publisher
     * @param msg
     * @Description: 发送消息
     * @return: void
     */
    public void expend(String publisher, BaseEvent msg) {
        for (ISubscriber subscriber : subscribers) {
            try {
                subscriber.onEvent(publisher, msg);
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            }
        }
    }

    /**
     * 取出消息发送给订阅者
     */
    private class LoopRunnable implements Runnable {
        @Override
        public void run() {
            Msg m;
            while ((m = getMsg()) != null) {
                for (ISubscriber subscriber : subscribers) {
                    try {
                        subscriber.onEvent(m.getPublisher(), m.getMsg());
                    } catch (Exception e) {
                        ExceptionHandler.handle(e);
                    }
                }
            }
        }

        private Msg getMsg() {
            Msg msg;
            try {
                msg = queue.take();
            } catch (InterruptedException e) {
                return null;
            }
            return msg;
        }
    }

    /**
     * @Description: 消息类
     */
    private class Msg implements Comparable {

        private String publisher;
        private BaseEvent m;
        private MsgPriority priority;

        public Msg(String publisher, BaseEvent m, MsgPriority priority) {
            this.publisher = publisher;
            this.m = m;
            this.priority = priority;
        }

        public String getPublisher() {
            return publisher;
        }

        public BaseEvent getMsg() {
            return m;
        }

        public void setMsg(BaseEvent m) {
            this.m = m;
        }

        @Override
        public int compareTo(Object arg) {
            Msg task = (Msg) arg;
            return priority.getPriority() < task.priority.getPriority() ? 1 : priority.getPriority() == task.priority.getPriority() ? 0 : -1;
        }
    }
}

