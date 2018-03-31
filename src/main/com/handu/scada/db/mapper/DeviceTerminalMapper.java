package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceTerminal;
import main.com.handu.scada.db.bean.DeviceTerminalExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceTerminalMapper extends CommonMapper {
    long countByExample(DeviceTerminalExample example);

    int deleteByExample(DeviceTerminalExample example);

    int deleteByPrimaryKey(String terminalid);

    int insert(DeviceTerminal record);

    int insertSelective(DeviceTerminal record);

    List<DeviceTerminal> selectByExampleWithBLOBs(DeviceTerminalExample example);

    List<DeviceTerminal> selectByExample(DeviceTerminalExample example);

    DeviceTerminal selectByPrimaryKey(String terminalid);

    int updateByExampleSelective(@Param("record") DeviceTerminal record, @Param("example") DeviceTerminalExample example);

    int updateByExampleWithBLOBs(@Param("record") DeviceTerminal record, @Param("example") DeviceTerminalExample example);

    int updateByExample(@Param("record") DeviceTerminal record, @Param("example") DeviceTerminalExample example);

    int updateByPrimaryKeySelective(DeviceTerminal record);

    int updateByPrimaryKeyWithBLOBs(DeviceTerminal record);

    int updateByPrimaryKey(DeviceTerminal record);
}