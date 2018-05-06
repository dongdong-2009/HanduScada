package main.com.handu.scada.protocol;


import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;

/**
 * Created by 柳梦 on 2017/12/12.
 */
public interface IProtocol {

    /**
     * 校验
     *
     * @param bytes
     * @return
     */
    default boolean valid(byte[] bytes) {
        return false;
    }

    /**
     * 解析设备地址
     *
     * @param buff
     */
    default void getAddress(byte[] buff) {
    }

    /**
     * 解析数据
     *
     * @param mediaData
     * @return
     */
    default ProtocolLayerData parse(MediaData mediaData) {
        return null;
    }

    /**
     * 下发命令
     *
     * @param protocolLayerData
     * @return
     */
    default MediaData sendCommand(ProtocolLayerData protocolLayerData) {
        return null;
    }
}
