package main.com.handu.scada.db.mapper;

import java.util.Date;
import java.util.List;
import main.com.handu.scada.db.bean.DeviceHistoryRemotesignalling;
import main.com.handu.scada.db.bean.DeviceHistoryRemotesignallingExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceHistoryRemotesignallingMapper extends CommonMapper {
    long countByExample(DeviceHistoryRemotesignallingExample example);

    int deleteByExample(DeviceHistoryRemotesignallingExample example);

    int deleteByPrimaryKey(@Param("remotesignallingid") String remotesignallingid, @Param("recordtime") Date recordtime);

    int insert(DeviceHistoryRemotesignalling record);

    int insertSelective(DeviceHistoryRemotesignalling record);

    List<DeviceHistoryRemotesignalling> selectByExampleWithBLOBs(DeviceHistoryRemotesignallingExample example);

    List<DeviceHistoryRemotesignalling> selectByExample(DeviceHistoryRemotesignallingExample example);

    DeviceHistoryRemotesignalling selectByPrimaryKey(@Param("remotesignallingid") String remotesignallingid, @Param("recordtime") Date recordtime);

    int updateByExampleSelective(@Param("record") DeviceHistoryRemotesignalling record, @Param("example") DeviceHistoryRemotesignallingExample example);

    int updateByExampleWithBLOBs(@Param("record") DeviceHistoryRemotesignalling record, @Param("example") DeviceHistoryRemotesignallingExample example);

    int updateByExample(@Param("record") DeviceHistoryRemotesignalling record, @Param("example") DeviceHistoryRemotesignallingExample example);

    int updateByPrimaryKeySelective(DeviceHistoryRemotesignalling record);

    int updateByPrimaryKeyWithBLOBs(DeviceHistoryRemotesignalling record);

    int updateByPrimaryKey(DeviceHistoryRemotesignalling record);
}