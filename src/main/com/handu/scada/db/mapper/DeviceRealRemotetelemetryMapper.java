package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceRealRemotetelemetry;
import main.com.handu.scada.db.bean.DeviceRealRemotetelemetryExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceRealRemotetelemetryMapper extends CommonMapper {
    long countByExample(DeviceRealRemotetelemetryExample example);

    int deleteByExample(DeviceRealRemotetelemetryExample example);

    int deleteByPrimaryKey(@Param("deviceid") String deviceid, @Param("dataitem") String dataitem);

    int insert(DeviceRealRemotetelemetry record);

    int insertSelective(DeviceRealRemotetelemetry record);

    List<DeviceRealRemotetelemetry> selectByExample(DeviceRealRemotetelemetryExample example);

    DeviceRealRemotetelemetry selectByPrimaryKey(@Param("deviceid") String deviceid, @Param("dataitem") String dataitem);

    int updateByExampleSelective(@Param("record") DeviceRealRemotetelemetry record, @Param("example") DeviceRealRemotetelemetryExample example);

    int updateByExample(@Param("record") DeviceRealRemotetelemetry record, @Param("example") DeviceRealRemotetelemetryExample example);

    int updateByPrimaryKeySelective(DeviceRealRemotetelemetry record);

    int updateByPrimaryKey(DeviceRealRemotetelemetry record);
}