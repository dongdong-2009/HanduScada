package main.com.handu.scada.db.mapper.common;

import main.com.handu.scada.db.bean.DeviceRealRemotesignalling;
import main.com.handu.scada.db.bean.DeviceRealRemotetelemetry;
import main.com.handu.scada.db.bean.DeviceRemoteindexs;
import main.com.handu.scada.db.bean.common.DeviceDtuCacheResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 柳梦 on 2017/12/07.
 */
public interface CommonMapper {

    int insertBySql(String sql);

    int updateBySql(String sql);

    int deleteBySql(String sql);

    List<Map<String, Object>> selectListBySql(String sql);

    Map<String, Object> selectOneBySql(String sql);

    /**
     * 查询设备，dtu关联缓存
     *
     * @param ports
     * @return
     */
    List<DeviceDtuCacheResult> selectDeviceDtuCacheResult(@Param("ports") List<String> ports);

    /**
     * 设备索引缓存
     *
     * @param ports
     * @return
     */
    List<DeviceRemoteindexs> selectDeviceRemoteIndexes(@Param("ports") List<String> ports);

    /**
     * 设备遥测缓存
     *
     * @param ports
     * @return
     */
    List<DeviceRealRemotetelemetry> selectDeviceRealRemoteTelemetry(@Param("ports") List<String> ports);

    /**
     * 设备遥信缓存
     *
     * @param ports
     * @return
     */
    List<DeviceRealRemotesignalling> selectDeviceRealRemoteSignalling(@Param("ports") List<String> ports);
}
