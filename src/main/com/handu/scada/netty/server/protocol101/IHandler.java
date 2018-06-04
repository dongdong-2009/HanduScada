package main.com.handu.scada.netty.server.protocol101;

/**
 * Created by 柳梦 on 2018/05/29.
 */
public interface IHandler {

    /**
     * 收到数据
     */
    void channelRead(byte[] bytes);

    /**
     * 开始链路请求
     */
    void startLinkQuest();

    /**
     * 主站回复
     */
    void sendConfirm();
}
