package main.com.handu.scada.netty.client.dtu;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.event.Subscriber;
import main.com.handu.scada.event.events.BaseEvent;
import main.com.handu.scada.event.events.DownProtocolEvent;
import main.com.handu.scada.event.subscribe.ISubscriber;
import main.com.handu.scada.event.subscribe.SubscribePublish;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.server.dtu.DtuChannelManager;
import main.com.handu.scada.netty.server.dtu.DtuNetworkConnection;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.thread.MyThreadPoolExecutor;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;

import java.util.Queue;
import java.util.concurrent.ExecutorService;

/**
 * Created by 柳梦 on 2017/12/26.
 */

@Subscriber
public class DtuTcpClient implements ISubscriber, IClient {

    //线程池
    private ExecutorService service = MyThreadPoolExecutor.getInstance();

    private DtuTcpClient() {
    }

    @Override
    public void subscribe(SubscribePublish subscribePublish) {
        subscribePublish.subscribe(this);
    }

    @Override
    public void unSubscribe(SubscribePublish subscribePublish) {
        subscribePublish.unSubscribe(this);
    }

    @Override
    public void onEvent(String publisher, BaseEvent event) {
        if (event instanceof DownProtocolEvent) {
            ProtocolLayerData protocolLayerData = (ProtocolLayerData) event.data;
            if (protocolLayerData != null) {
                service.execute(() -> send(event.priority, protocolLayerData));
            }
        }
    }

    @Override
    public void send(ProtocolLayerData protocolLayerData) {
        send(MsgPriority.LOW, protocolLayerData);
    }

    @Override
    public void send(MsgPriority priority, ProtocolLayerData protocolLayerData) {
        if (protocolLayerData != null) {
            if (!StringsUtils.isEmpty(protocolLayerData.DTUString)) {
                String clientId = DtuChannelManager.getClientId(protocolLayerData.DTUString);
                if (clientId != null) {
                    DtuNetworkConnection state = DtuChannelManager.getNetworkState(clientId);
                    if (state != null && state.getChannel().isActive()) {
                        ChannelHandlerContext context = state.getContext();
                        if (context == null) return;
                        try {
                            MediaData mediaData = state.notifyDownParse(protocolLayerData);
                            if (mediaData != null && mediaData.CommandData != null) {
                                mediaData.isWaitReceive = protocolLayerData.isWaitReceive;
                                mediaData.cmdTypeEnum = protocolLayerData.CmdType;
                                mediaData.deviceTypeEnum = protocolLayerData.deviceTypeEnum;
                                //如果上一次发送时间大于3倍心跳并且队列数量不为0，清空队列避免发送重复命令
                                if (state.getQueueSize() > 0 && System.currentTimeMillis() - state.getLastSendTime() > Config.getHeartBeat() * 3) {
                                    state.clearQueue();
                                }
                                //如果不忙或者上一次发送时间已过去1分钟设备还未回复则继续发送
                                if (!state.isBusy() || System.currentTimeMillis() - state.getLastSendTime() > 60 * 1000) {
                                    ByteBuf byteBuf = context.alloc().buffer(mediaData.CommandData.length);
                                    byteBuf.writeBytes(mediaData.CommandData);
                                    state.setLastSendTime(System.currentTimeMillis());
                                    state.setBusy(mediaData.isWaitReceive);
                                    printCommand(mediaData, false);
                                    sendCommand(context, byteBuf);
                                } else {
                                    //主动下发命令
                                    if (priority == MsgPriority.HIGH) {
                                        Queue<MediaData> highQueue = state.getHighQueue();
                                        highQueue.add(mediaData);
                                    }
                                    //自动采集
                                    else if (priority == MsgPriority.LOW) {
                                        Queue<MediaData> lowQueue = state.getLowQueue();
                                        lowQueue.add(mediaData);
                                    }
                                    printCommand(mediaData, true);
                                }
                            }
                        } catch (Exception e) {
                            ExceptionHandler.handle(e);
                        }
                    }
                } else {
                    LogUtils.error("dtuAddress " + protocolLayerData.DTUString + " is not connection!");
                }
            }
        }
    }

    /**
     * 打印下发命令报文
     *
     * @param mediaData
     */
    private void printCommand(MediaData mediaData, boolean isBusy) {
        if (Config.isDebug) {
            if (mediaData.deviceTypeEnum != null) {
                String resultStr = HexUtils.byteArrayToHexStr(mediaData.CommandData);
                if (!isBusy) {
                    LogUtils.info("sendCommand-->dtuAddress=" + mediaData.DTUString + ",deviceType=" + mediaData.deviceTypeEnum.name() + ",cmdType=" + mediaData.cmdTypeEnum.name() + ",data=" + resultStr);
                } else {
                    LogUtils.info("sendCommand into queue-->dtuAddress=" + mediaData.DTUString + ",deviceType=" + mediaData.deviceTypeEnum.name() + ",cmdType=" + mediaData.cmdTypeEnum.name() + ",data=" + resultStr);
                }
            }
        }
    }

    @Override
    public void sendCommand(ChannelHandlerContext ctx, ByteBuf data) {
        ChannelFuture f = ctx.writeAndFlush(data);
        //f.addListener(future -> data.release());
    }

    @Override
    public void sendCommand(Channel channel, byte[] data) {
        ChannelFuture f = channel.writeAndFlush(data);
        //f.addListener(future -> {});
    }
}
