package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceRcdTrialswitchlog;
import main.com.handu.scada.db.bean.DeviceRcdTrialswitchlogExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceRcdTrialswitchlogMapper extends CommonMapper {
    long countByExample(DeviceRcdTrialswitchlogExample example);

    int deleteByExample(DeviceRcdTrialswitchlogExample example);

    int deleteByPrimaryKey(String oid);

    int insert(DeviceRcdTrialswitchlog record);

    int insertSelective(DeviceRcdTrialswitchlog record);

    List<DeviceRcdTrialswitchlog> selectByExample(DeviceRcdTrialswitchlogExample example);

    DeviceRcdTrialswitchlog selectByPrimaryKey(String oid);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceRcdTrialswitchlog record, @Param("example") DeviceRcdTrialswitchlogExample example);

    int updateByExample(@Param("tripEventRecord") DeviceRcdTrialswitchlog record, @Param("example") DeviceRcdTrialswitchlogExample example);

    int updateByPrimaryKeySelective(DeviceRcdTrialswitchlog record);

    int updateByPrimaryKey(DeviceRcdTrialswitchlog record);
}