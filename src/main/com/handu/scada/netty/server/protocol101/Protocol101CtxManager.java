package main.com.handu.scada.netty.server.protocol101;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 柳梦 on 2018/03/13.
 */
public class Protocol101CtxManager {

    /**
     * 链接管理
     */
    private static ConcurrentHashMap<String, Protocol101CommandHandler> connectionMap = new ConcurrentHashMap<>();


    /**
     * @param address 设备地址
     * @param handler handler
     */
    public static void addHandler(String address, Protocol101CommandHandler handler) {
        connectionMap.put(address, handler);
    }

    /**
     * 下线移除链接
     *
     * @param address
     */
    public static void removeHandler(String address) {
        if (address != null) {
            connectionMap.remove(address);
        }
    }

    /**
     * 获取链接
     *
     * @param address
     * @return
     */
    public static Protocol101CommandHandler getHandler(String address) {
        if (address != null) return connectionMap.get(address);
        return null;
    }
}
