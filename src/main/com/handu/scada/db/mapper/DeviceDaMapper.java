package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceDa;
import main.com.handu.scada.db.bean.DeviceDaExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceDaMapper extends CommonMapper {
    long countByExample(DeviceDaExample example);

    int deleteByExample(DeviceDaExample example);

    int deleteByPrimaryKey(String oid);

    int insert(DeviceDa record);

    int insertSelective(DeviceDa record);

    List<DeviceDa> selectByExample(DeviceDaExample example);

    DeviceDa selectByPrimaryKey(String oid);

    int updateByExampleSelective(@Param("record") DeviceDa record, @Param("example") DeviceDaExample example);

    int updateByExample(@Param("record") DeviceDa record, @Param("example") DeviceDaExample example);

    int updateByPrimaryKeySelective(DeviceDa record);

    int updateByPrimaryKey(DeviceDa record);
}