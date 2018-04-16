package main.com.handu.scada.netty.server;

/**
 * 消息类型
 */
public enum MsgType {
    ONLINE,//上线
    DEVICE_INFO,//设备信息
    SERIAL_PORT,//报文信息
    HEARTBEAT,//心跳
    LOGIN,//登录
    CONNECTION,//开关链路成功
}