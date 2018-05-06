package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceRealRemotesignalling;
import main.com.handu.scada.db.bean.DeviceRealRemotesignallingExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceRealRemotesignallingMapper extends CommonMapper {
    long countByExample(DeviceRealRemotesignallingExample example);

    int deleteByExample(DeviceRealRemotesignallingExample example);

    int deleteByPrimaryKey(@Param("deviceid") String deviceid, @Param("dataitem") String dataitem);

    int insert(DeviceRealRemotesignalling record);

    int insertSelective(DeviceRealRemotesignalling record);

    List<DeviceRealRemotesignalling> selectByExample(DeviceRealRemotesignallingExample example);

    DeviceRealRemotesignalling selectByPrimaryKey(@Param("deviceid") String deviceid, @Param("dataitem") String dataitem);

    int updateByExampleSelective(@Param("record") DeviceRealRemotesignalling record, @Param("example") DeviceRealRemotesignallingExample example);

    int updateByExample(@Param("record") DeviceRealRemotesignalling record, @Param("example") DeviceRealRemotesignallingExample example);

    int updateByPrimaryKeySelective(DeviceRealRemotesignalling record);

    int updateByPrimaryKey(DeviceRealRemotesignalling record);
}