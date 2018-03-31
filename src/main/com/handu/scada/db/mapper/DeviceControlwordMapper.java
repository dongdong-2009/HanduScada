package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceControlword;
import main.com.handu.scada.db.bean.DeviceControlwordExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceControlwordMapper extends CommonMapper {
    long countByExample(DeviceControlwordExample example);

    int deleteByExample(DeviceControlwordExample example);

    int deleteByPrimaryKey(String deviceid);

    int insert(DeviceControlword record);

    int insertSelective(DeviceControlword record);

    List<DeviceControlword> selectByExample(DeviceControlwordExample example);

    DeviceControlword selectByPrimaryKey(String deviceid);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceControlword record, @Param("example") DeviceControlwordExample example);

    int updateByExample(@Param("tripEventRecord") DeviceControlword record, @Param("example") DeviceControlwordExample example);

    int updateByPrimaryKeySelective(DeviceControlword record);

    int updateByPrimaryKey(DeviceControlword record);
}