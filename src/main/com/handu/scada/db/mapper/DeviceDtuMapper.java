package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceDtu;
import main.com.handu.scada.db.bean.DeviceDtuExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceDtuMapper extends CommonMapper {
    long countByExample(DeviceDtuExample example);

    int deleteByExample(DeviceDtuExample example);

    int deleteByPrimaryKey(String oid);

    int insert(DeviceDtu record);

    int insertSelective(DeviceDtu record);

    List<DeviceDtu> selectByExample(DeviceDtuExample example);

    DeviceDtu selectByPrimaryKey(String oid);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceDtu record, @Param("example") DeviceDtuExample example);

    int updateByExample(@Param("tripEventRecord") DeviceDtu record, @Param("example") DeviceDtuExample example);

    int updateByPrimaryKeySelective(DeviceDtu record);

    int updateByPrimaryKey(DeviceDtu record);
}