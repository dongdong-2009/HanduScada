package main.com.handu.scada.db.mapper;

import main.com.handu.scada.db.bean.DeviceHistoryRemotetelemetry;
import main.com.handu.scada.db.bean.DeviceHistoryRemotetelemetryExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DeviceHistoryRemotetelemetryMapper extends CommonMapper {
    long countByExample(DeviceHistoryRemotetelemetryExample example);

    int deleteByExample(DeviceHistoryRemotetelemetryExample example);

    int deleteByPrimaryKey(@Param("remotetelemetryid") String remotetelemetryid, @Param("recordtime") Date recordtime);

    int insert(DeviceHistoryRemotetelemetry record);

    int insertSelective(DeviceHistoryRemotetelemetry record);

    List<DeviceHistoryRemotetelemetry> selectByExampleWithBLOBs(DeviceHistoryRemotetelemetryExample example);

    List<DeviceHistoryRemotetelemetry> selectByExample(DeviceHistoryRemotetelemetryExample example);

    DeviceHistoryRemotetelemetry selectByPrimaryKey(@Param("remotetelemetryid") String remotetelemetryid, @Param("recordtime") Date recordtime);

    int updateByExampleSelective(@Param("record") DeviceHistoryRemotetelemetry record, @Param("example") DeviceHistoryRemotetelemetryExample example);

    int updateByExampleWithBLOBs(@Param("record") DeviceHistoryRemotetelemetry record, @Param("example") DeviceHistoryRemotetelemetryExample example);

    int updateByExample(@Param("record") DeviceHistoryRemotetelemetry record, @Param("example") DeviceHistoryRemotetelemetryExample example);

    int updateByPrimaryKeySelective(DeviceHistoryRemotetelemetry record);

    int updateByPrimaryKeyWithBLOBs(DeviceHistoryRemotetelemetry record);

    int updateByPrimaryKey(DeviceHistoryRemotetelemetry record);
}