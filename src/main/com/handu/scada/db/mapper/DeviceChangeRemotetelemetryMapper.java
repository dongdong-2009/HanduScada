package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceChangeRemotetelemetry;
import main.com.handu.scada.db.bean.DeviceChangeRemotetelemetryExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceChangeRemotetelemetryMapper extends CommonMapper {
    long countByExample(DeviceChangeRemotetelemetryExample example);

    int deleteByExample(DeviceChangeRemotetelemetryExample example);

    int deleteByPrimaryKey(String remotetelemetryid);

    int insert(DeviceChangeRemotetelemetry record);

    int insertSelective(DeviceChangeRemotetelemetry record);

    List<DeviceChangeRemotetelemetry> selectByExampleWithBLOBs(DeviceChangeRemotetelemetryExample example);

    List<DeviceChangeRemotetelemetry> selectByExample(DeviceChangeRemotetelemetryExample example);

    DeviceChangeRemotetelemetry selectByPrimaryKey(String remotetelemetryid);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceChangeRemotetelemetry record, @Param("example") DeviceChangeRemotetelemetryExample example);

    int updateByExampleWithBLOBs(@Param("tripEventRecord") DeviceChangeRemotetelemetry record, @Param("example") DeviceChangeRemotetelemetryExample example);

    int updateByExample(@Param("tripEventRecord") DeviceChangeRemotetelemetry record, @Param("example") DeviceChangeRemotetelemetryExample example);

    int updateByPrimaryKeySelective(DeviceChangeRemotetelemetry record);

    int updateByPrimaryKeyWithBLOBs(DeviceChangeRemotetelemetry record);

    int updateByPrimaryKey(DeviceChangeRemotetelemetry record);
}