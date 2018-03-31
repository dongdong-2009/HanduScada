package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceLowvoltage;
import main.com.handu.scada.db.bean.DeviceLowvoltageExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceLowvoltageMapper extends CommonMapper {
    long countByExample(DeviceLowvoltageExample example);

    int deleteByExample(DeviceLowvoltageExample example);

    int deleteByPrimaryKey(String lowuid);

    int insert(DeviceLowvoltage record);

    int insertSelective(DeviceLowvoltage record);

    List<DeviceLowvoltage> selectByExample(DeviceLowvoltageExample example);

    DeviceLowvoltage selectByPrimaryKey(String lowuid);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceLowvoltage record, @Param("example") DeviceLowvoltageExample example);

    int updateByExample(@Param("tripEventRecord") DeviceLowvoltage record, @Param("example") DeviceLowvoltageExample example);

    int updateByPrimaryKeySelective(DeviceLowvoltage record);

    int updateByPrimaryKey(DeviceLowvoltage record);
}