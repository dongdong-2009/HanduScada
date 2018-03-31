package main.com.handu.scada.protocol;


import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;

/**
 * Created by 柳梦 on 2017/12/12.
 */
public interface IProtocol {

    /// <summary>
    /// 解析数据
    /// </summary>
    ProtocolLayerData parse(MediaData mediaData);

    /// <summary>
    /// 解析设备地址
    /// </summary>
    /// <param name="buff"></param>
    void getAddress(byte[] buff);

    /// <summary>
    /// 校验
    /// </summary>
    /// <param name="buff"></param>
    /// <returns></returns>
    boolean valid(byte[] bytes);

    /**
     * 下发命令
     */
    MediaData sendCommand(ProtocolLayerData protocolLayerData);
}
