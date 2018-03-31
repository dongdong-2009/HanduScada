package main.com.handu.scada.netty.server.dtu;


import main.com.handu.scada.config.Config;
import main.com.handu.scada.netty.server.MsgType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Dtu通道管理
 */
public class DtuChannelManager {

    private static ConcurrentHashMap<String, DtuNetworkConnection> networkStateMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, String> dtuIdMap = new ConcurrentHashMap<>();

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
            if (dtuIdMap.containsKey(dtuAddress)) {
                dtuIdMap.remove(dtuAddress);
            }
        }
        if (networkStateMap.containsKey(clientId)) {
            networkStateMap.remove(clientId);
        }
    }

    /**
     * 更新dtuId
     *
     * @param clientId
     * @param dtuAddress
     */
    private static void updateDtuAddress(String clientId, String dtuAddress, MsgType type) {
        if (networkStateMap.containsKey(clientId)) {
            DtuNetworkConnection state = networkStateMap.get(clientId);
            if (state != null) {
                state.setDtuAddress(dtuAddress);
                state.getCallback().online(clientId, dtuAddress, type);
            }
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
        removeUselessChannel(clientId, dtuAddress);
        updateDtuAddress(clientId, dtuAddress, type);
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
        if (networkStateMap.containsKey(clientId)) {
            DtuNetworkConnection state = networkStateMap.get(clientId);
            if (state != null) {
                return state.getDtuAddress();
            }
        }
        return null;
    }

    /**
     * 移除无用链接
     *
     * @param clientId
     * @param dtuAddress
     */
    public static void removeUselessChannel(String clientId, String dtuAddress) {
        for (Map.Entry<String, DtuNetworkConnection> entry : networkStateMap.entrySet()) {
            DtuNetworkConnection state = entry.getValue();
            if (state.getDtuAddress() != null) {
                if (state.getDtuAddress().equals(dtuAddress)) {
                    if (!entry.getKey().equals(clientId)) {
                        networkStateMap.remove(entry.getKey());
                    }
                }
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
        String clientId = DtuChannelManager.getClientId(dtuAddress);
        if (clientId == null) return false;
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(clientId);
        if (state != null && state.getContext() != null && state.getChannel() != null) {
            long time = System.currentTimeMillis() - state.getLastReceiptTime();
            boolean isActive = state.getChannel().isActive() && time < Config.getHeartBeat() * 3;
            if (!isActive) {
                state.getContext().close();
                if (dtuIdMap.containsKey(dtuAddress)) {
                    dtuIdMap.remove(dtuAddress);
                }
                if (networkStateMap.containsKey(clientId)) {
                    networkStateMap.remove(clientId);
                }
            }
            return isActive;
        }
        return false;
    }
}
