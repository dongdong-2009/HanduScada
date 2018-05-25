package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceIntelligentswitchTelesignal;
import main.com.handu.scada.db.bean.DeviceIntelligentswitchTelesignalExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceIntelligentswitchTelesignalMapper extends CommonMapper {
    long countByExample(DeviceIntelligentswitchTelesignalExample example);

    int deleteByExample(DeviceIntelligentswitchTelesignalExample example);

    int deleteByPrimaryKey(@Param("deviceid") String deviceid, @Param("dataitem") String dataitem);

    int insert(DeviceIntelligentswitchTelesignal record);

    int insertSelective(DeviceIntelligentswitchTelesignal record);

    List<DeviceIntelligentswitchTelesignal> selectByExample(DeviceIntelligentswitchTelesignalExample example);

    DeviceIntelligentswitchTelesignal selectByPrimaryKey(@Param("deviceid") String deviceid, @Param("dataitem") String dataitem);

    int updateByExampleSelective(@Param("record") DeviceIntelligentswitchTelesignal record, @Param("example") DeviceIntelligentswitchTelesignalExample example);

    int updateByExample(@Param("record") DeviceIntelligentswitchTelesignal record, @Param("example") DeviceIntelligentswitchTelesignalExample example);

    int updateByPrimaryKeySelective(DeviceIntelligentswitchTelesignal record);

    int updateByPrimaryKey(DeviceIntelligentswitchTelesignal record);
}