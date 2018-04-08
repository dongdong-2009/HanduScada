package main.com.handu.scada.quartz.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.event.EventManager;
import main.com.handu.scada.event.events.DownProtocolEvent;
import main.com.handu.scada.netty.client.dtu.MsgPriority;
import main.com.handu.scada.netty.server.dtu.DtuChannelManager;
import main.com.handu.scada.netty.server.dtu.DtuNetworkConnection;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceTypeEnum;
import main.com.handu.scada.quartz.job.CommonJob;
import main.com.handu.scada.utils.LogUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 柳梦 on 2018/01/17.
 */
public class DtuCommand extends CommonJob {

    private static DtuCommand deviceCommand;

    public static DtuCommand getInstance() {
        if (deviceCommand == null) deviceCommand = new DtuCommand();
        return deviceCommand;
    }

    private DeviceCmdTypeEnum[] baseData = new DeviceCmdTypeEnum[]{
            DeviceCmdTypeEnum.Voltage,
            DeviceCmdTypeEnum.Current,
            DeviceCmdTypeEnum.ResidualAndMaxPhase
    };

    private DeviceCmdTypeEnum[] dateTime = new DeviceCmdTypeEnum[]{
            DeviceCmdTypeEnum.RunDate,
            DeviceCmdTypeEnum.RunTime,
    };

    /**
     * 下发基础数据采集
     */
    private void baseData() {
        send(MsgPriority.HIGH, DeviceTypeEnum.LP, baseData);
    }


    /**
     * 跌落装置总召
     */
    private void fallSwitch() {
        send(MsgPriority.LOW, DeviceTypeEnum.FALL_TYPE_SWITCH, DeviceCmdTypeEnum.ALL_CALL);
    }

    /**
     * 测温总召
     */
    private void temperature() {
        send(MsgPriority.HIGH, DeviceTypeEnum.THERMOMETRY, DeviceCmdTypeEnum.ALL_CALL);
    }

    /**
     * 日期时间
     */
    private void dateTime() {
        send(MsgPriority.HIGH, DeviceTypeEnum.LP, dateTime);
    }

    private void readControlWordParameterModule() {
        send(MsgPriority.HIGH, DeviceTypeEnum.LP, DeviceCmdTypeEnum.ReadControlWordParameterModule);
    }

    /**
     * 下发运行状态
     */
    private void runState() {
        send(MsgPriority.HIGH, DeviceTypeEnum.LP, DeviceCmdTypeEnum.RunState);
    }

    /**
     * 读通讯地址
     */
    public void readPostalAddress() {
        send(MsgPriority.HIGH, DeviceTypeEnum.LP, DeviceCmdTypeEnum.ReadPostalAddress);
    }

    /**
     * 下发告警事件
     */
    private void protectorTripEventRecord() {
        send(MsgPriority.HIGH, DeviceTypeEnum.LP, DeviceCmdTypeEnum.ProtectorTripEventRecord);
    }

    public void sendByValue(int value) {
        switch (value) {
            case 1000:
                baseData();
                break;
            case 1001:
                dateTime();
                break;
            case 1002:
                runState();
                break;
            case 1003:
                protectorTripEventRecord();
                break;
            case 1004:
                readControlWordParameterModule();
                break;
            case 1005:
                temperature();
                break;
            case 1006:
                fallSwitch();
                break;
            case 1007:
                readPostalAddress();
            default:
                LogUtils.error("not find cmd by value " + value, true);
                break;
        }
    }

    /**
     * 获取dtu信息
     *
     * @param dtuAddresses
     */
    public void readDtuInfo(String... dtuAddresses) {
        for (String dtuAddress : dtuAddresses) {
            ProtocolLayerData data = new ProtocolLayerData();
            data.CmdType = DeviceCmdTypeEnum.DTU_INFO;
            data.DTUString = dtuAddress;
            EventManager.getInstance().publish(new DownProtocolEvent(MsgPriority.HIGH, data), MsgPriority.HIGH);
        }
    }

    /**
     * 重启dtus
     *
     * @param dtuAddresses
     */
    public void restartDtu(String... dtuAddresses) {
        for (String dtuAddress : dtuAddresses) {
            ProtocolLayerData data = new ProtocolLayerData();
            data.CmdType = DeviceCmdTypeEnum.DTU_RESTART;
            data.DTUString = dtuAddress;
            EventManager.getInstance().publish(new DownProtocolEvent(MsgPriority.LOW, data), MsgPriority.HIGH);
        }
    }

    /**
     * 执行sql语句
     *
     * @param sql
     */
    public void executeSql(String sql) {
        try {
            JsonArray array = new JsonArray();
            SqlSession sqlSession = MyBatisUtil.getSqlSession();
            CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
            List<Map<String, Object>> list = mapper.selectListBySql(sql);
            for (Map<String, Object> map : list) {
                JsonObject object = new JsonObject();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    object.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
                }
                array.add(object);
            }
            LogUtils.info("sql execute success---" + (array.size() > 0 ? array.toString() : ""), true);
        } catch (Exception e) {
            LogUtils.error(" sql execute error!");
        }
    }

    /**
     * 获取客户端数量
     */
    public void getClientNumber() {
        LogUtils.info("client number:" + DtuChannelManager.getNetworkStateMapCount() + "--dtu number:" + DtuChannelManager.getDtuMapCount(), true);
        if (Config.isDebug) {
            ConcurrentHashMap<String, DtuNetworkConnection> map = DtuChannelManager.getNetworkStateMap();
            for (Map.Entry<String, DtuNetworkConnection> entry : map.entrySet()) {
                LogUtils.error(entry.getKey() + "--" + entry.getValue().getDtuAddress(), true);
            }
        }
    }

    /**
     * 对时
     */
    public void broadcastTime(String... dtuAddresses) {
        for (String dtuAddress : dtuAddresses) {
            ProtocolLayerData data = new ProtocolLayerData();
            data.DTUString = dtuAddress;
            data.deviceTypeEnum = DeviceTypeEnum.LP;
            data.HasDTUHead = true;
            data.CmdType = DeviceCmdTypeEnum.BroadcastTime;
            data.isWaitReceive = false;
            EventManager.getInstance().publish(new DownProtocolEvent(MsgPriority.HIGH, data), MsgPriority.HIGH);
        }
    }
}
