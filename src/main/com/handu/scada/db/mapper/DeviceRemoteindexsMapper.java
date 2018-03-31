package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceRemoteindexs;
import main.com.handu.scada.db.bean.DeviceRemoteindexsExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceRemoteindexsMapper extends CommonMapper {
    long countByExample(DeviceRemoteindexsExample example);

    int deleteByExample(DeviceRemoteindexsExample example);

    int deleteByPrimaryKey(String remoteindexsid);

    int insert(DeviceRemoteindexs record);

    int insertSelective(DeviceRemoteindexs record);

    List<DeviceRemoteindexs> selectByExample(DeviceRemoteindexsExample example);

    DeviceRemoteindexs selectByPrimaryKey(String remoteindexsid);

    int updateByExampleSelective(@Param("tripEventRecord") DeviceRemoteindexs record, @Param("example") DeviceRemoteindexsExample example);

    int updateByExample(@Param("tripEventRecord") DeviceRemoteindexs record, @Param("example") DeviceRemoteindexsExample example);

    int updateByPrimaryKeySelective(DeviceRemoteindexs record);

    int updateByPrimaryKey(DeviceRemoteindexs record);
}