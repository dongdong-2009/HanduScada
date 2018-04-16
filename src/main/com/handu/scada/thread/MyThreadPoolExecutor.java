package main.com.handu.scada.thread;

import main.com.handu.scada.exception.ExceptionHandler;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 柳梦 on 2018/01/24.
 * 自定义线程池
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {


    private static MyThreadPoolExecutor executor;

    /**
     * 核心线程数为CPU数量+1（cpu数量获取方式Runtime.getRuntime().availableProcessors()，
     * 最大线程数为CPU数量×2+1，
     * 在以后使用线程池的过程中可以参考这个再结合自己的实际情况来配置参数。
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final long KEEP_ALIVE = 0;
    private static final AtomicInteger count = new AtomicInteger();

    private MyThreadPoolExecutor() {
        super(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                r -> {
                    String threadName = MyThreadPoolExecutor.class.getSimpleName() + count.incrementAndGet();
                    return new Thread(r, threadName);
                },
                (r, executor) -> {
                    try {
                        //如果任务量很大，还要求每个任务都处理成功，要对提交的任务进行阻塞提交，
                        //重写拒绝机制，改为阻塞提交。保证不抛弃一个任务
                        //核心改造点，由blockingqueue的offer改成put阻塞方法
                        executor.getQueue().put(r);
                    } catch (InterruptedException e) {
                        ExceptionHandler.handle(e);
                    }
                });
    }

    public static MyThreadPoolExecutor getInstance() {
        if (executor == null) {
            executor = new MyThreadPoolExecutor();
        }
        return executor;
    }
}
