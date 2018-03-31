package main.com.handu.scada.netty.server.switch101;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 柳梦 on 2018/03/13.
 */
public class SwitchCtxManager {

    /**
     * 链接管理
     */
    private static ConcurrentHashMap<String, SwitchNetworkConnection> connectionMap = new ConcurrentHashMap<>();


    /**
     * @param connectionId 客户端连接id
     * @param connection   connection
     */
    public static void addConnection(String connectionId, SwitchNetworkConnection connection) {
        connectionMap.put(connectionId, connection);
    }

    /**
     * 下线移除链接
     *
     * @param connectionId
     */
    public static void removeClient(String connectionId) {
        if (connectionId != null && connectionMap.containsKey(connectionId)) {
            connectionMap.remove(connectionId);
        }
    }

    /**
     * 获取链接
     *
     * @param connectionId
     * @return
     */
    public static SwitchNetworkConnection getSwitchNetworkConnection(String connectionId) {
        if (connectionId != null && connectionMap.containsKey(connectionId)) return connectionMap.get(connectionId);
        return null;
    }
}
