package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceFaultindicatorHistoryTelemetry;
import main.com.handu.scada.db.bean.DeviceFaultindicatorHistoryTelemetryExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceFaultindicatorHistoryTelemetryMapper extends CommonMapper {
    long countByExample(DeviceFaultindicatorHistoryTelemetryExample example);

    int deleteByExample(DeviceFaultindicatorHistoryTelemetryExample example);

    int deleteByPrimaryKey(String id);

    int insert(DeviceFaultindicatorHistoryTelemetry record);

    int insertSelective(DeviceFaultindicatorHistoryTelemetry record);

    List<DeviceFaultindicatorHistoryTelemetry> selectByExample(DeviceFaultindicatorHistoryTelemetryExample example);

    DeviceFaultindicatorHistoryTelemetry selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DeviceFaultindicatorHistoryTelemetry record, @Param("example") DeviceFaultindicatorHistoryTelemetryExample example);

    int updateByExample(@Param("record") DeviceFaultindicatorHistoryTelemetry record, @Param("example") DeviceFaultindicatorHistoryTelemetryExample example);

    int updateByPrimaryKeySelective(DeviceFaultindicatorHistoryTelemetry record);

    int updateByPrimaryKey(DeviceFaultindicatorHistoryTelemetry record);
}