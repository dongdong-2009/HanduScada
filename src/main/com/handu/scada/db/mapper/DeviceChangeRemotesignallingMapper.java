package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceChangeRemotesignalling;
import main.com.handu.scada.db.bean.DeviceChangeRemotesignallingExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceChangeRemotesignallingMapper extends CommonMapper {
    long countByExample(DeviceChangeRemotesignallingExample example);

    int deleteByExample(DeviceChangeRemotesignallingExample example);

    int deleteByPrimaryKey(String remotesignallingid);

    int insert(DeviceChangeRemotesignalling record);

    int insertSelective(DeviceChangeRemotesignalling record);

    List<DeviceChangeRemotesignalling> selectByExampleWithBLOBs(DeviceChangeRemotesignallingExample example);

    List<DeviceChangeRemotesignalling> selectByExample(DeviceChangeRemotesignallingExample example);

    DeviceChangeRemotesignalling selectByPrimaryKey(String remotesignallingid);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceChangeRemotesignalling record, @Param("example") DeviceChangeRemotesignallingExample example);

    int updateByExampleWithBLOBs(@Param("tripEventRecord") DeviceChangeRemotesignalling record, @Param("example") DeviceChangeRemotesignallingExample example);

    int updateByExample(@Param("tripEventRecord") DeviceChangeRemotesignalling record, @Param("example") DeviceChangeRemotesignallingExample example);

    int updateByPrimaryKeySelective(DeviceChangeRemotesignalling record);

    int updateByPrimaryKeyWithBLOBs(DeviceChangeRemotesignalling record);

    int updateByPrimaryKey(DeviceChangeRemotesignalling record);
}