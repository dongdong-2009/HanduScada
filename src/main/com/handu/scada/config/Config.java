package main.com.handu.scada.config;

import main.com.handu.scada.utils.StringsUtils;

public class Config {

    /**
     * 系统名称
     */
    private static String SystemName = "总漏保在线监测系统";

    /**
     * 超时时间
     */
    private static int timeout = 60;

    /**
     * 心跳
     */
    private static int heartBeat = 60 * 5;

    /**
     * IP地址
     */
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
    private static String device101Ports;
    /**
     * 是否处于debug模式
     */
    public static boolean isDebug = false;

    /**
     * 是否打印sql语句
     */
    public static boolean isSQLPrint = false;

    public static String getDevice101Ports() {
        return device101Ports;
    }

    public static void setDevice101Ports(String device101Ports) {
        if (StringsUtils.isNotEmpty(Config.device101Ports)) {
            Config.device101Ports += "," + device101Ports;
        } else {
            Config.device101Ports = device101Ports;
        }
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

    public static int getTimeout() {
        return timeout;
    }

    public static void setTimeout(int timeout) {
        Config.timeout = timeout;
    }
}
