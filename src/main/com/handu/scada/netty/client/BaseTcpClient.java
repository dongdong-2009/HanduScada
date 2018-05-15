package main.com.handu.scada.netty.client;

import main.com.handu.scada.event.subscribe.ISubscriber;
import main.com.handu.scada.thread.MyThreadPoolExecutor;

import java.util.concurrent.ExecutorService;

/**
 * Created by 柳梦 on 2018/05/10.
 */
public abstract class BaseTcpClient implements ISubscriber {
    //线程池
    protected ExecutorService service = MyThreadPoolExecutor.getInstance();
}
