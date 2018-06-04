package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceFaultindicatorRealTelemetry;
import main.com.handu.scada.db.bean.DeviceFaultindicatorRealTelemetryExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceFaultindicatorRealTelemetryMapper extends CommonMapper {
    long countByExample(DeviceFaultindicatorRealTelemetryExample example);

    int deleteByExample(DeviceFaultindicatorRealTelemetryExample example);

    int deleteByPrimaryKey(@Param("deviceid") String deviceid, @Param("dataitem") String dataitem);

    int insert(DeviceFaultindicatorRealTelemetry record);

    int insertSelective(DeviceFaultindicatorRealTelemetry record);

    List<DeviceFaultindicatorRealTelemetry> selectByExample(DeviceFaultindicatorRealTelemetryExample example);

    DeviceFaultindicatorRealTelemetry selectByPrimaryKey(@Param("deviceid") String deviceid, @Param("dataitem") String dataitem);

    int updateByExampleSelective(@Param("record") DeviceFaultindicatorRealTelemetry record, @Param("example") DeviceFaultindicatorRealTelemetryExample example);

    int updateByExample(@Param("record") DeviceFaultindicatorRealTelemetry record, @Param("example") DeviceFaultindicatorRealTelemetryExample example);

    int updateByPrimaryKeySelective(DeviceFaultindicatorRealTelemetry record);

    int updateByPrimaryKey(DeviceFaultindicatorRealTelemetry record);
}