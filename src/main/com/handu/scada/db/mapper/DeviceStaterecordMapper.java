package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceStaterecord;
import main.com.handu.scada.db.bean.DeviceStaterecordExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceStaterecordMapper extends CommonMapper {
    long countByExample(DeviceStaterecordExample example);

    int deleteByExample(DeviceStaterecordExample example);

    int deleteByPrimaryKey(String recordid);

    int insert(DeviceStaterecord record);

    int insertSelective(DeviceStaterecord record);

    List<DeviceStaterecord> selectByExampleWithBLOBs(DeviceStaterecordExample example);

    List<DeviceStaterecord> selectByExample(DeviceStaterecordExample example);

    DeviceStaterecord selectByPrimaryKey(String recordid);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceStaterecord record, @Param("example") DeviceStaterecordExample example);

    int updateByExampleWithBLOBs(@Param("tripEventRecord") DeviceStaterecord record, @Param("example") DeviceStaterecordExample example);

    int updateByExample(@Param("tripEventRecord") DeviceStaterecord record, @Param("example") DeviceStaterecordExample example);

    int updateByPrimaryKeySelective(DeviceStaterecord record);

    int updateByPrimaryKeyWithBLOBs(DeviceStaterecord record);

    int updateByPrimaryKey(DeviceStaterecord record);
}