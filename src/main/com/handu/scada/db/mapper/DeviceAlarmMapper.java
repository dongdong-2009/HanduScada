package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceAlarm;
import main.com.handu.scada.db.bean.DeviceAlarmExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceAlarmMapper extends CommonMapper {
    long countByExample(DeviceAlarmExample example);

    int deleteByExample(DeviceAlarmExample example);

    int deleteByPrimaryKey(String alarmid);

    int insert(DeviceAlarm record);

    int insertSelective(DeviceAlarm record);

    List<DeviceAlarm> selectByExampleWithBLOBs(DeviceAlarmExample example);

    List<DeviceAlarm> selectByExample(DeviceAlarmExample example);

    DeviceAlarm selectByPrimaryKey(String alarmid);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceAlarm record, @Param("example") DeviceAlarmExample example);

    int updateByExampleWithBLOBs(@Param("tripEventRecord") DeviceAlarm record, @Param("example") DeviceAlarmExample example);

    int updateByExample(@Param("tripEventRecord") DeviceAlarm record, @Param("example") DeviceAlarmExample example);

    int updateByPrimaryKeySelective(DeviceAlarm record);

    int updateByPrimaryKeyWithBLOBs(DeviceAlarm record);

    int updateByPrimaryKey(DeviceAlarm record);
}