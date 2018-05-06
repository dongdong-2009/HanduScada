package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceHmOverload;
import main.com.handu.scada.db.bean.DeviceHmOverloadExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceHmOverloadMapper extends CommonMapper {
    long countByExample(DeviceHmOverloadExample example);

    int deleteByExample(DeviceHmOverloadExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DeviceHmOverload record);

    int insertSelective(DeviceHmOverload record);

    List<DeviceHmOverload> selectByExample(DeviceHmOverloadExample example);

    DeviceHmOverload selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DeviceHmOverload record, @Param("example") DeviceHmOverloadExample example);

    int updateByExample(@Param("record") DeviceHmOverload record, @Param("example") DeviceHmOverloadExample example);

    int updateByPrimaryKeySelective(DeviceHmOverload record);

    int updateByPrimaryKey(DeviceHmOverload record);
}