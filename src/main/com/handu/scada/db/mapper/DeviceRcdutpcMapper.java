package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceRcdutpc;
import main.com.handu.scada.db.bean.DeviceRcdutpcExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceRcdutpcMapper extends CommonMapper {
    long countByExample(DeviceRcdutpcExample example);

    int deleteByExample(DeviceRcdutpcExample example);

    int deleteByPrimaryKey(String utpcid);

    int insert(DeviceRcdutpc record);

    int insertSelective(DeviceRcdutpc record);

    List<DeviceRcdutpc> selectByExample(DeviceRcdutpcExample example);

    DeviceRcdutpc selectByPrimaryKey(String utpcid);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceRcdutpc record, @Param("example") DeviceRcdutpcExample example);

    int updateByExample(@Param("tripEventRecord") DeviceRcdutpc record, @Param("example") DeviceRcdutpcExample example);

    int updateByPrimaryKeySelective(DeviceRcdutpc record);

    int updateByPrimaryKey(DeviceRcdutpc record);
}