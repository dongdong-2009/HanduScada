package main.com.handu.scada.db.mapper.common;

import main.com.handu.scada.business.message.MsgAdditionProperty;
import main.com.handu.scada.db.bean.common.AdditionProperty;
import main.com.handu.scada.db.bean.common.Device101CacheResult;
import main.com.handu.scada.db.bean.common.DeviceCacheResult;
import main.com.handu.scada.db.bean.common.DtuCacheResult;
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
     * @param ports
     * @return
     */
    List<DtuCacheResult> selectDtuCacheResult(@Param("ports") List<String> ports, @Param("dtuIds") List<String> dtuIds);


    /**
     * @param ports
     * @return
     */
    List<DeviceCacheResult> selectDeviceCacheResult(@Param("ports") List<String> ports, @Param("deviceIds") List<String> deviceIds);


    /**
     * 查询101协议设备缓存
     *
     * @param ports
     * @param deviceIds
     * @return
     */
    List<Device101CacheResult> selectDevice101CacheResult(@Param("ports") List<String> ports, @Param("deviceIds") List<String> deviceIds);

    /**
     * 根据id删除infoChanges
     *
     * @return
     */
    int deleteInfoChanges(@Param("ids") List<String> ids);


    /**
     * 查询设备附加属性
     *
     * @param sql
     * @return
     */
    List<AdditionProperty> selectDeviceAdditionProperty(@Param("sql") String sql);


    /**
     * 获取短信发送附加信息
     *
     * @param deviceIds
     * @return
     */
    List<MsgAdditionProperty> selectMsgAdditionProperty(@Param("deviceIds") List<String> deviceIds);
}
