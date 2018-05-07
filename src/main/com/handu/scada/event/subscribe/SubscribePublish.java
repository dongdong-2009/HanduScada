package main.com.handu.scada.event.subscribe;

import main.com.handu.scada.event.events.BaseEvent;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.client.dtu.MsgPriority;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 订阅器类
 * Created by 柳梦 on 2017/12/11.
 */
public class SubscribePublish {

    //订阅器存储队列，采用优先级队列，优先级越高则先处理
    private BlockingQueue<Msg> queue = new LinkedBlockingDeque<>();
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
            ExceptionHandler.print(e);
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
            try {
                while ((m = queue.take()) != null) {
                    for (ISubscriber subscriber : subscribers) {
                        try {
                            subscriber.onEvent(m.getPublisher(), m.getMsg());
                        } catch (Exception e) {
                            ExceptionHandler.print(e);
                        }
                    }
                }
            } catch (InterruptedException e) {
                ExceptionHandler.handle(e);
            }
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

        public void setPublisher(String publisher) {
            this.publisher = publisher;
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

