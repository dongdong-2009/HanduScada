package main.com.handu.scada.db.service;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.db.bean.common.DtuCacheResult;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.utils.DBServiceUtil;
import main.com.handu.scada.utils.StringsUtils;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 */
public abstract class BaseDBService extends DBServiceUtil {

    protected StringBuilder sb = new StringBuilder();
    protected int num = 0;
    protected List<DeviceData> dataList;

    private String getJointSql() {
        jointSql();
        if (num > 0)
            return sb.deleteCharAt(sb.lastIndexOf(",")).toString();
        return "";
    }

    protected String sql() {
        if (StringsUtils.isNotEmpty(startSql())) {
            return startSql() + " " + getJointSql() + " " + (endSql() == null ? "" : endSql());
        }
        return null;
    }

    protected int submit(CommonMapper mapper, List<DeviceData> dataList) {
        this.dataList = dataList;
        String sql = sql();
        if (num == 0) return 0;
        if (mapper != null) {
            return mapper.insertBySql(sql);
        }
        return 0;
    }

    protected abstract String startSql();

    protected abstract String endSql();

    protected abstract void jointSql();

    /**
     * 根据dtuId获取deviceId
     *
     * @param dtuAddress
     * @return
     */
    protected String getDtuIdByDtuAddress(String dtuAddress) {
        if (StringsUtils.isNotEmpty(dtuAddress)) {
            DtuCacheResult dtuCacheResult = MyCacheManager.getDtuCacheResult(dtuAddress);
            if (dtuCacheResult != null) return dtuCacheResult.getDtuId();
        }
        return null;
    }
}
