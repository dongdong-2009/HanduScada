package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceFaultindicatorRealTelesignal;
import main.com.handu.scada.db.bean.DeviceFaultindicatorRealTelesignalExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceFaultindicatorRealTelesignalMapper extends CommonMapper {
    long countByExample(DeviceFaultindicatorRealTelesignalExample example);

    int deleteByExample(DeviceFaultindicatorRealTelesignalExample example);

    int deleteByPrimaryKey(@Param("deviceid") String deviceid, @Param("dataitem") String dataitem);

    int insert(DeviceFaultindicatorRealTelesignal record);

    int insertSelective(DeviceFaultindicatorRealTelesignal record);

    List<DeviceFaultindicatorRealTelesignal> selectByExample(DeviceFaultindicatorRealTelesignalExample example);

    DeviceFaultindicatorRealTelesignal selectByPrimaryKey(@Param("deviceid") String deviceid, @Param("dataitem") String dataitem);

    int updateByExampleSelective(@Param("record") DeviceFaultindicatorRealTelesignal record, @Param("example") DeviceFaultindicatorRealTelesignalExample example);

    int updateByExample(@Param("record") DeviceFaultindicatorRealTelesignal record, @Param("example") DeviceFaultindicatorRealTelesignalExample example);

    int updateByPrimaryKeySelective(DeviceFaultindicatorRealTelesignal record);

    int updateByPrimaryKey(DeviceFaultindicatorRealTelesignal record);
}