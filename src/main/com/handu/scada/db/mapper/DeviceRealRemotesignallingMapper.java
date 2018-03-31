package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceRealRemotesignalling;
import main.com.handu.scada.db.bean.DeviceRealRemotesignallingExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceRealRemotesignallingMapper extends CommonMapper {
    long countByExample(DeviceRealRemotesignallingExample example);

    int deleteByExample(DeviceRealRemotesignallingExample example);

    int deleteByPrimaryKey(String remotesignallingid);

    int insert(DeviceRealRemotesignalling record);

    int insertSelective(DeviceRealRemotesignalling record);

    List<DeviceRealRemotesignalling> selectByExampleWithBLOBs(DeviceRealRemotesignallingExample example);

    List<DeviceRealRemotesignalling> selectByExample(DeviceRealRemotesignallingExample example);

    DeviceRealRemotesignalling selectByPrimaryKey(String remotesignallingid);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceRealRemotesignalling record, @Param("example") DeviceRealRemotesignallingExample example);

    int updateByExampleWithBLOBs(@Param("tripEventRecord") DeviceRealRemotesignalling record, @Param("example") DeviceRealRemotesignallingExample example);

    int updateByExample(@Param("tripEventRecord") DeviceRealRemotesignalling record, @Param("example") DeviceRealRemotesignallingExample example);

    int updateByPrimaryKeySelective(DeviceRealRemotesignalling record);

    int updateByPrimaryKeyWithBLOBs(DeviceRealRemotesignalling record);

    int updateByPrimaryKey(DeviceRealRemotesignalling record);
}