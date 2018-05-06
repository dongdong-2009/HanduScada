package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceHmLowVoltage;
import main.com.handu.scada.db.bean.DeviceHmLowVoltageExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceHmLowVoltageMapper extends CommonMapper {
    long countByExample(DeviceHmLowVoltageExample example);

    int deleteByExample(DeviceHmLowVoltageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DeviceHmLowVoltage record);

    int insertSelective(DeviceHmLowVoltage record);

    List<DeviceHmLowVoltage> selectByExample(DeviceHmLowVoltageExample example);

    DeviceHmLowVoltage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DeviceHmLowVoltage record, @Param("example") DeviceHmLowVoltageExample example);

    int updateByExample(@Param("record") DeviceHmLowVoltage record, @Param("example") DeviceHmLowVoltageExample example);

    int updateByPrimaryKeySelective(DeviceHmLowVoltage record);

    int updateByPrimaryKey(DeviceHmLowVoltage record);
}