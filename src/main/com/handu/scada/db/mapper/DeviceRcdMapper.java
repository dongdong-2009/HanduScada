package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceRcd;
import main.com.handu.scada.db.bean.DeviceRcdExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceRcdMapper extends CommonMapper {
    long countByExample(DeviceRcdExample example);

    int deleteByExample(DeviceRcdExample example);

    int deleteByPrimaryKey(String oid);

    int insert(DeviceRcd record);

    int insertSelective(DeviceRcd record);

    List<DeviceRcd> selectByExample(DeviceRcdExample example);

    DeviceRcd selectByPrimaryKey(String oid);

    int updateByExampleSelective(@Param("record") DeviceRcd record, @Param("example") DeviceRcdExample example);

    int updateByExample(@Param("record") DeviceRcd record, @Param("example") DeviceRcdExample example);

    int updateByPrimaryKeySelective(DeviceRcd record);

    int updateByPrimaryKey(DeviceRcd record);
}