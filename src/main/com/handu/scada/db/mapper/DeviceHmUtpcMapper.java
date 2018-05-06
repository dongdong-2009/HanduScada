package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceHmUtpc;
import main.com.handu.scada.db.bean.DeviceHmUtpcExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceHmUtpcMapper extends CommonMapper {
    long countByExample(DeviceHmUtpcExample example);

    int deleteByExample(DeviceHmUtpcExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DeviceHmUtpc record);

    int insertSelective(DeviceHmUtpc record);

    List<DeviceHmUtpc> selectByExample(DeviceHmUtpcExample example);

    DeviceHmUtpc selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DeviceHmUtpc record, @Param("example") DeviceHmUtpcExample example);

    int updateByExample(@Param("record") DeviceHmUtpc record, @Param("example") DeviceHmUtpcExample example);

    int updateByPrimaryKeySelective(DeviceHmUtpc record);

    int updateByPrimaryKey(DeviceHmUtpc record);
}