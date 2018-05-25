package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceIntelligentswitchTelemetry;
import main.com.handu.scada.db.bean.DeviceIntelligentswitchTelemetryExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceIntelligentswitchTelemetryMapper extends CommonMapper {
    long countByExample(DeviceIntelligentswitchTelemetryExample example);

    int deleteByExample(DeviceIntelligentswitchTelemetryExample example);

    int deleteByPrimaryKey(@Param("deviceid") String deviceid, @Param("dataitem") String dataitem);

    int insert(DeviceIntelligentswitchTelemetry record);

    int insertSelective(DeviceIntelligentswitchTelemetry record);

    List<DeviceIntelligentswitchTelemetry> selectByExample(DeviceIntelligentswitchTelemetryExample example);

    DeviceIntelligentswitchTelemetry selectByPrimaryKey(@Param("deviceid") String deviceid, @Param("dataitem") String dataitem);

    int updateByExampleSelective(@Param("record") DeviceIntelligentswitchTelemetry record, @Param("example") DeviceIntelligentswitchTelemetryExample example);

    int updateByExample(@Param("record") DeviceIntelligentswitchTelemetry record, @Param("example") DeviceIntelligentswitchTelemetryExample example);

    int updateByPrimaryKeySelective(DeviceIntelligentswitchTelemetry record);

    int updateByPrimaryKey(DeviceIntelligentswitchTelemetry record);
}