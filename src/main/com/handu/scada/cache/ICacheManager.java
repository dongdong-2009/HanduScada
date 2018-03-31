package main.com.handu.scada.cache;

import java.util.Map;
import java.util.Set;

/**
 * Created by 柳梦 on 2017/12/19.
 */
public interface ICacheManager {

    /**
     * 存入缓存
     *
     * @param key
     * @param cache
     */
    void putCache(String key, EntityCache cache);

    /**
     * 存入缓存
     *
     * @param key
     * @param data
     */
    void putCache(String key, Object data, long timeOut);

    /**
     * 获取对应缓存
     *
     * @param key
     * @return
     */
    EntityCache getCacheByKey(String key);

    /**
     * 获取对应缓存
     *
     * @param key
     * @return
     */
    Object getCacheDataByKey(String key);

    <T> T getDataByKey(String key);

    /**
     * 获取所有缓存
     *
     * @return
     */
    Map<String, EntityCache> getCacheAll();

    void refreshDataByKey(String key, Object data);

    /**
     * 判断是否在缓存中
     *
     * @param key
     * @return
     */
    boolean isContains(String key);

    /**
     * 清除所有缓存
     */
    void clearAll();

    /**
     * 清除对应缓存
     *
     * @param key
     */
    void clearByKey(String key);

    /**
     * 缓存是否超时失效
     *
     * @param key
     * @return
     */
    boolean isTimeOut(String key);

    /**
     * 获取所有key
     *
     * @return
     */
    Set<String> getAllKeys();
}
