package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceRealRemotetelemetry;
import main.com.handu.scada.db.bean.DeviceRealRemotetelemetryExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceRealRemotetelemetryMapper extends CommonMapper {
    long countByExample(DeviceRealRemotetelemetryExample example);

    int deleteByExample(DeviceRealRemotetelemetryExample example);

    int deleteByPrimaryKey(String remotetelemetryid);

    int insert(DeviceRealRemotetelemetry record);

    int insertSelective(DeviceRealRemotetelemetry record);

    List<DeviceRealRemotetelemetry> selectByExampleWithBLOBs(DeviceRealRemotetelemetryExample example);

    List<DeviceRealRemotetelemetry> selectByExample(DeviceRealRemotetelemetryExample example);

    DeviceRealRemotetelemetry selectByPrimaryKey(String remotetelemetryid);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceRealRemotetelemetry record, @Param("example") DeviceRealRemotetelemetryExample example);

    int updateByExampleWithBLOBs(@Param("tripEventRecord") DeviceRealRemotetelemetry record, @Param("example") DeviceRealRemotetelemetryExample example);

    int updateByExample(@Param("tripEventRecord") DeviceRealRemotetelemetry record, @Param("example") DeviceRealRemotetelemetryExample example);

    int updateByPrimaryKeySelective(DeviceRealRemotetelemetry record);

    int updateByPrimaryKeyWithBLOBs(DeviceRealRemotetelemetry record);

    int updateByPrimaryKey(DeviceRealRemotetelemetry record);
}