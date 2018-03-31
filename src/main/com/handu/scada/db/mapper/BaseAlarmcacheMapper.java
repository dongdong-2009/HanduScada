package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.BaseAlarmcache;
import main.com.handu.scada.db.bean.BaseAlarmcacheExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface BaseAlarmcacheMapper extends CommonMapper {
    long countByExample(BaseAlarmcacheExample example);

    int deleteByExample(BaseAlarmcacheExample example);

    int deleteByPrimaryKey(String oid);

    int insert(BaseAlarmcache record);

    int insertSelective(BaseAlarmcache record);

    List<BaseAlarmcache> selectByExampleWithBLOBs(BaseAlarmcacheExample example);

    List<BaseAlarmcache> selectByExample(BaseAlarmcacheExample example);

    BaseAlarmcache selectByPrimaryKey(String oid);

    int updateByExampleSelective(@Param("tripEventRecord") BaseAlarmcache record, @Param("example") BaseAlarmcacheExample example);

    int updateByExampleWithBLOBs(@Param("tripEventRecord") BaseAlarmcache record, @Param("example") BaseAlarmcacheExample example);

    int updateByExample(@Param("tripEventRecord") BaseAlarmcache record, @Param("example") BaseAlarmcacheExample example);

    int updateByPrimaryKeySelective(BaseAlarmcache record);

    int updateByPrimaryKeyWithBLOBs(BaseAlarmcache record);

    int updateByPrimaryKey(BaseAlarmcache record);
}