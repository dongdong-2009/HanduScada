package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceInfochanges;
import main.com.handu.scada.db.bean.DeviceInfochangesExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceInfochangesMapper extends CommonMapper {
    long countByExample(DeviceInfochangesExample example);

    int deleteByExample(DeviceInfochangesExample example);

    int deleteByPrimaryKey(String oid);

    int insert(DeviceInfochanges record);

    int insertSelective(DeviceInfochanges record);

    List<DeviceInfochanges> selectByExample(DeviceInfochangesExample example);

    DeviceInfochanges selectByPrimaryKey(String oid);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceInfochanges record, @Param("example") DeviceInfochangesExample example);

    int updateByExample(@Param("tripEventRecord") DeviceInfochanges record, @Param("example") DeviceInfochangesExample example);

    int updateByPrimaryKeySelective(DeviceInfochanges record);

    int updateByPrimaryKey(DeviceInfochanges record);
}