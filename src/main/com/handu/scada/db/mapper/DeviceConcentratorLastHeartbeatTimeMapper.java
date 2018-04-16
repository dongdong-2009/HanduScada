package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceConcentratorLastHeartbeatTime;
import main.com.handu.scada.db.bean.DeviceConcentratorLastHeartbeatTimeExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceConcentratorLastHeartbeatTimeMapper extends CommonMapper {
    long countByExample(DeviceConcentratorLastHeartbeatTimeExample example);

    int deleteByExample(DeviceConcentratorLastHeartbeatTimeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DeviceConcentratorLastHeartbeatTime record);

    int insertSelective(DeviceConcentratorLastHeartbeatTime record);

    List<DeviceConcentratorLastHeartbeatTime> selectByExample(DeviceConcentratorLastHeartbeatTimeExample example);

    DeviceConcentratorLastHeartbeatTime selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DeviceConcentratorLastHeartbeatTime record, @Param("example") DeviceConcentratorLastHeartbeatTimeExample example);

    int updateByExample(@Param("record") DeviceConcentratorLastHeartbeatTime record, @Param("example") DeviceConcentratorLastHeartbeatTimeExample example);

    int updateByPrimaryKeySelective(DeviceConcentratorLastHeartbeatTime record);

    int updateByPrimaryKey(DeviceConcentratorLastHeartbeatTime record);
}