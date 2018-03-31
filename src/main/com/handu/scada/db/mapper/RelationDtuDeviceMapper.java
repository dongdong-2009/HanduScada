package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.RelationDtuDevice;
import main.com.handu.scada.db.bean.RelationDtuDeviceExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface RelationDtuDeviceMapper extends CommonMapper {
    long countByExample(RelationDtuDeviceExample example);

    int deleteByExample(RelationDtuDeviceExample example);

    int deleteByPrimaryKey(String id);

    int insert(RelationDtuDevice record);

    int insertSelective(RelationDtuDevice record);

    List<RelationDtuDevice> selectByExample(RelationDtuDeviceExample example);

    RelationDtuDevice selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RelationDtuDevice record, @Param("example") RelationDtuDeviceExample example);

    int updateByExample(@Param("record") RelationDtuDevice record, @Param("example") RelationDtuDeviceExample example);

    int updateByPrimaryKeySelective(RelationDtuDevice record);

    int updateByPrimaryKey(RelationDtuDevice record);
}