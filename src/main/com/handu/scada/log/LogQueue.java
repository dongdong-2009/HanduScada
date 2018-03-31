package main.com.handu.scada.log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by 柳梦 on 2018/03/26.
 */
public class LogQueue {

    //队列大小
    private static final int QUEUE_MAX_SIZE = 1000;

    private static LogQueue queue = new LogQueue();

    //阻塞队列
    private BlockingQueue<Log> blockingQueue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

    private LogQueue() {
    }

    public static LogQueue getInstance() {
        return queue;
    }

    /**
     * 消息入队
     *
     * @param log
     * @return
     */
    public boolean push(Log log) {
        return this.blockingQueue.add(log);//队列满了就抛出异常，不阻塞
    }

    /**
     * 消息出队
     *
     * @return
     */
    public Log poll() {
        Log result = null;
        try {
            result = this.blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取队列大小
     *
     * @return
     */
    public int size() {
        return this.blockingQueue.size();
    }
}
