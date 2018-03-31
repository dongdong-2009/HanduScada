package main.com.handu.scada.switch101.protocol;

import main.com.handu.scada.switch101.protocol.bean.BaseData;
import main.com.handu.scada.switch101.protocol.bean.DownData;

/**
 * Created by 柳梦 on 2018/03/14.
 */
public interface ISwitchProtocol {

    /**
     * 校验
     *
     * @return
     */
    boolean valid(byte[] data);

    /**
     * 下发
     *
     * @return
     */
    BaseData send(DownData data);

    /**
     * 解析
     *
     * @return
     */
    BaseData parse(byte[] data) throws InstantiationException, IllegalAccessException;

}
