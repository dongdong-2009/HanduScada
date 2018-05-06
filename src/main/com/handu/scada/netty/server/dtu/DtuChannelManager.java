package main.com.handu.scada.netty.server.dtu;


import io.netty.channel.ChannelHandlerContext;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.netty.server.MsgType;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 * Dtu通道管理
 */
public class DtuChannelManager {

    private static final ConcurrentHashMap<String, DtuNetworkConnection> networkStateMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, String> dtuIdMap = new ConcurrentHashMap<>();

    /**
     * @param clientId     客户端连接id
     * @param networkState NetworkState
     */
    public static void addClient(String clientId, DtuNetworkConnection networkState) {
        networkStateMap.put(clientId, networkState);
    }

    /**
     * 获取链接集合
     *
     * @return
     */
    public static ConcurrentHashMap<String, DtuNetworkConnection> getNetworkStateMap() {
        return networkStateMap;
    }

    /**
     * @return
     */
    public static int getNetworkStateMapCount() {
        return networkStateMap.size();
    }

    /**
     * @return
     */
    public static int getDtuMapCount() {
        return dtuIdMap.size();
    }

    /**
     * @param clientId
     * @return
     */
    public static DtuNetworkConnection getNetworkState(String clientId) {
        if (clientId == null) return null;
        return networkStateMap.get(clientId);
    }

    /**
     * 移除连接和dtu
     *
     * @param clientId
     */
    public static void removeClient(String clientId, String dtuAddress) {
        if (dtuAddress != null) {
            dtuIdMap.remove(dtuAddress);
        }
        if (clientId != null) {
            networkStateMap.remove(clientId);
        }
    }


    /**
     * 更新
     *
     * @param clientId
     * @param dtuAddress
     */
    public static void update(String clientId, String dtuAddress, MsgType type) {
        dtuIdMap.put(dtuAddress, clientId);
        removeUselessChannel(clientId, dtuAddress, type);
    }

    /**
     * 获取链接id
     *
     * @param dtuAddress
     * @return
     */
    public static String getClientId(String dtuAddress) {
        if (dtuAddress == null) return null;
        return dtuIdMap.get(dtuAddress);
    }

    /**
     * 获取dtuId
     *
     * @param clientId
     * @return
     */
    public static String getDtuAddress(String clientId) {
        DtuNetworkConnection state = networkStateMap.get(clientId);
        if (state != null) {
            return state.getDtuAddress();
        }
        return null;
    }

    /**
     * 移除无用链接并更新dtu连接
     *
     * @param clientId
     * @param dtuAddress
     */
    public static void removeUselessChannel(String clientId, String dtuAddress, MsgType type) {
        synchronized (networkStateMap) {
            networkStateMap
                    .entrySet()
                    .stream()
                    .filter(e -> Objects.equals(e.getValue().getDtuAddress(), dtuAddress) && !Objects.equals(e.getKey(), clientId))
                    .collect(Collectors.toList())
                    .forEach(e -> {
                        DtuNetworkConnection c = networkStateMap.get(e.getKey());
                        ChannelHandlerContext context = c.getContext();
                        if (context != null) context.close();
                        networkStateMap.remove(e.getKey());
                    });
            DtuNetworkConnection connection = networkStateMap.get(clientId);
            if (connection != null) {
                connection.setDtuAddress(dtuAddress);
                connection.getCallback().online(clientId, dtuAddress, type);
            }
        }
    }

    /**
     * 根据dtu地址获取链接的有效性
     * 如果无效则退出连接
     *
     * @param dtuAddress
     * @return
     */
    public static boolean getDeviceChannelIsActive(String dtuAddress) {
        String clientId = getClientId(dtuAddress);
        if (clientId == null) return false;
        DtuNetworkConnection state = getNetworkState(clientId);
        if (state != null && state.getContext() != null && state.getChannel() != null) {
            long time = System.currentTimeMillis() - state.getLastReceiptTime();
            boolean isActive = state.getChannel().isActive() && time < Config.getHeartBeat() * 3;
            if (!isActive) {
                state.getContext().close();
                removeClient(clientId, dtuAddress);
            }
            return isActive;
        }
        return false;
    }
}
