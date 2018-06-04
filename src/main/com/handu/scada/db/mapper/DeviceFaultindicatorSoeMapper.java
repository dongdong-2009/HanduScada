package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceFaultindicatorSoe;
import main.com.handu.scada.db.bean.DeviceFaultindicatorSoeExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceFaultindicatorSoeMapper extends CommonMapper {
    long countByExample(DeviceFaultindicatorSoeExample example);

    int deleteByExample(DeviceFaultindicatorSoeExample example);

    int deleteByPrimaryKey(String soeid);

    int insert(DeviceFaultindicatorSoe record);

    int insertSelective(DeviceFaultindicatorSoe record);

    List<DeviceFaultindicatorSoe> selectByExample(DeviceFaultindicatorSoeExample example);

    DeviceFaultindicatorSoe selectByPrimaryKey(String soeid);

    int updateByExampleSelective(@Param("record") DeviceFaultindicatorSoe record, @Param("example") DeviceFaultindicatorSoeExample example);

    int updateByExample(@Param("record") DeviceFaultindicatorSoe record, @Param("example") DeviceFaultindicatorSoeExample example);

    int updateByPrimaryKeySelective(DeviceFaultindicatorSoe record);

    int updateByPrimaryKey(DeviceFaultindicatorSoe record);
}