package main.com.handu.scada.cache;

/**
 * Created by 柳梦 on 2017/12/19.
 */
public class EntityCache {

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public long getLastRefreshTime() {
        return lastRefreshTime;
    }

    public void setLastRefreshTime(long lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }

    /**
     * 保存的数据
     */
    private Object data;

    /**
     * 设置数据失效时间,为0表示永不失效
     */
    private long timeOut;

    /**
     * 最后刷新时间
     */
    private long lastRefreshTime;

    public EntityCache(Object data, long timeOut, long lastRefreshTime) {
        this.data = data;
        this.timeOut = timeOut;
        this.lastRefreshTime = lastRefreshTime;
    }

    public EntityCache(Object data) {
        this.data = data;
        this.timeOut = 0;
        this.lastRefreshTime = System.currentTimeMillis();
    }
}