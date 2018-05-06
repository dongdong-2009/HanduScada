package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceHmRealAfn0c25;
import main.com.handu.scada.db.bean.DeviceHmRealAfn0c25Example;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceHmRealAfn0c25Mapper extends CommonMapper {
    long countByExample(DeviceHmRealAfn0c25Example example);

    int deleteByExample(DeviceHmRealAfn0c25Example example);

    int deleteByPrimaryKey(String dtuaddress);

    int insert(DeviceHmRealAfn0c25 record);

    int insertSelective(DeviceHmRealAfn0c25 record);

    List<DeviceHmRealAfn0c25> selectByExample(DeviceHmRealAfn0c25Example example);

    DeviceHmRealAfn0c25 selectByPrimaryKey(String dtuaddress);

    int updateByExampleSelective(@Param("record") DeviceHmRealAfn0c25 record, @Param("example") DeviceHmRealAfn0c25Example example);

    int updateByExample(@Param("record") DeviceHmRealAfn0c25 record, @Param("example") DeviceHmRealAfn0c25Example example);

    int updateByPrimaryKeySelective(DeviceHmRealAfn0c25 record);

    int updateByPrimaryKey(DeviceHmRealAfn0c25 record);
}