package main.com.handu.scada.protocol101.protocol;

import main.com.handu.scada.protocol101.protocol.bean.BaseData;

/**
 * Created by 柳梦 on 2018/03/14.
 */
public interface IProtocol101 {

    /**
     * 校验
     *
     * @return
     */
    default boolean valid(byte[] data) {
        return false;
    }

    /**
     * 下发
     *
     * @return
     */
    default BaseData send(BaseData data) {
        return null;
    }

    /**
     * 解析
     *
     * @return
     */
    default BaseData parse(byte[] data) throws InstantiationException, IllegalAccessException {
        return null;
    }
}
