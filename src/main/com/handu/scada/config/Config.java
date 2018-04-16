package main.com.handu.scada.config;

public class Config {

    private static String SystemName = "总漏保在线监测系统";

    /**
     * 心跳
     */
    private static int heartBeat = 60 * 5;

    private static String host = "127.0.0.1";
    /**
     * dtu端口
     */
    private static String dtuPorts;
    /**
     * 客户端操作端口
     */
    private static String cmdPort;
    /**
     * 开关端口
     */
    private static String switchPorts;
    /**
     * 是否处于debug模式
     */
    public static boolean isDebug = false;

    public static String getSwitchPorts() {
        return switchPorts;
    }

    public static void setSwitchPorts(String switchPorts) {
        Config.switchPorts = switchPorts;
    }

    public static String getDtuPorts() {
        return dtuPorts;
    }

    public static void setDtuPorts(String dtuPorts) {
        Config.dtuPorts = dtuPorts;
    }

    public static String getCmdPort() {
        return cmdPort;
    }

    public static void setCmdPort(String cmdPort) {
        Config.cmdPort = cmdPort;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        Config.host = host;
    }

    public static int getHeartBeat() {
        return heartBeat * 1000;
    }

    public static void setHeartBeat(int heartBeat) {
        Config.heartBeat = heartBeat;
    }

    public static String getSystemName() {
        return SystemName;
    }

    public static void setSystemName(String systemName) {
        SystemName = systemName;
    }
}
