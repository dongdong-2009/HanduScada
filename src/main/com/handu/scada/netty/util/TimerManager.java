package main.com.handu.scada.netty.util;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by 柳梦 on 2018/04/25.
 */
public class TimerManager {

    /**
     * 创建一个全局的timer，每一秒tick一次，长度为60
     */
    private HashedWheelTimer timer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 60);
    private ConcurrentHashMap<Integer, Timeout> hashMap = new ConcurrentHashMap<>();
    private static TimerManager ourInstance = new TimerManager();

    public static TimerManager getInstance() {
        return ourInstance;
    }

    private TimerManager() {
    }


    /**
     * 注册超时提醒对象
     *
     * @param timerTask
     * @param delay
     * @param unit
     */
    public void register(TimerTask timerTask, long delay, TimeUnit unit) {
        Timeout timeout = this.timer.newTimeout(timerTask, delay, unit);
        if (timeout != null) {
            hashMap.put(timerTask.hashCode(), timeout);
        }
    }

    /**
     * 取消注册超时提醒对象
     *
     * @param timerTask
     */
    public void unRegister(TimerTask timerTask) {
        Timeout timeout = hashMap.get(timerTask.hashCode());
        if (timeout != null) {
            if (!timeout.isCancelled()) {
                timeout.timer().stop();
                timeout.cancel();
                hashMap.remove(timerTask.hashCode());
            }
        }
    }
}
