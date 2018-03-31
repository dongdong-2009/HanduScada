package main.com.handu.scada.db.mapper;

import main.com.handu.scada.db.bean.DeviceCommunicationgroup;
import main.com.handu.scada.db.bean.DeviceCommunicationgroupExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceCommunicationgroupMapper extends CommonMapper {
    long countByExample(DeviceCommunicationgroupExample example);

    int deleteByExample(DeviceCommunicationgroupExample example);

    int deleteByPrimaryKey(String oid);

    int insert(DeviceCommunicationgroup record);

    int insertSelective(DeviceCommunicationgroup record);

    List<DeviceCommunicationgroup> selectByExample(DeviceCommunicationgroupExample example);

    DeviceCommunicationgroup selectByPrimaryKey(String oid);

    int updateByExampleSelective(@Param("record") DeviceCommunicationgroup record, @Param("example") DeviceCommunicationgroupExample example);

    int updateByExample(@Param("record") DeviceCommunicationgroup record, @Param("example") DeviceCommunicationgroupExample example);

    int updateByPrimaryKeySelective(DeviceCommunicationgroup record);

    int updateByPrimaryKey(DeviceCommunicationgroup record);
}