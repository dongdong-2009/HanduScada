package main.com.handu.scada.log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by 柳梦 on 2018/03/26.
 */
public class LogQueue {

    private static LogQueue singleton;

    //阻塞队列
    private BlockingQueue<Log> blockingQueue = new LinkedBlockingQueue<>();

    private LogQueue() {
    }

    public static LogQueue getInstance() {
        if (singleton == null) {
            synchronized (LogQueue.class) {
                if (singleton == null) {
                    singleton = new LogQueue();
                }
            }
        }
        return singleton;
    }

    /**
     * 消息入队
     *
     * @param log
     * @return
     */
    public boolean push(Log log) {
        try {
            //队列满了10秒放不进去就丢弃,抛出异常，不阻塞
            return this.blockingQueue.offer(log, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * 消息出队
     *
     * @return
     */
    public Log poll() {
        Log result;
        try {
            result = this.blockingQueue.take();
        } catch (InterruptedException e) {
            return null;
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
