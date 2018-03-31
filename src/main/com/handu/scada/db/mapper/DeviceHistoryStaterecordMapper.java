package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceHistoryStaterecord;
import main.com.handu.scada.db.bean.DeviceHistoryStaterecordExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceHistoryStaterecordMapper extends CommonMapper {
    long countByExample(DeviceHistoryStaterecordExample example);

    int deleteByExample(DeviceHistoryStaterecordExample example);

    int insert(DeviceHistoryStaterecord record);

    int insertSelective(DeviceHistoryStaterecord record);

    List<DeviceHistoryStaterecord> selectByExampleWithBLOBs(DeviceHistoryStaterecordExample example);

    List<DeviceHistoryStaterecord> selectByExample(DeviceHistoryStaterecordExample example);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceHistoryStaterecord record, @Param("example") DeviceHistoryStaterecordExample example);

    int updateByExampleWithBLOBs(@Param("tripEventRecord") DeviceHistoryStaterecord record, @Param("example") DeviceHistoryStaterecordExample example);

    int updateByExample(@Param("tripEventRecord") DeviceHistoryStaterecord record, @Param("example") DeviceHistoryStaterecordExample example);
}