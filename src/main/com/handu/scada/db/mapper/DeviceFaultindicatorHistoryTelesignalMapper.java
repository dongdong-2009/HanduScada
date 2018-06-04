package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.DeviceFaultindicatorHistoryTelesignal;
import main.com.handu.scada.db.bean.DeviceFaultindicatorHistoryTelesignalExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface DeviceFaultindicatorHistoryTelesignalMapper extends CommonMapper {
    long countByExample(DeviceFaultindicatorHistoryTelesignalExample example);

    int deleteByExample(DeviceFaultindicatorHistoryTelesignalExample example);

    int deleteByPrimaryKey(String id);

    int insert(DeviceFaultindicatorHistoryTelesignal record);

    int insertSelective(DeviceFaultindicatorHistoryTelesignal record);

    List<DeviceFaultindicatorHistoryTelesignal> selectByExample(DeviceFaultindicatorHistoryTelesignalExample example);

    DeviceFaultindicatorHistoryTelesignal selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DeviceFaultindicatorHistoryTelesignal record, @Param("example") DeviceFaultindicatorHistoryTelesignalExample example);

    int updateByExample(@Param("record") DeviceFaultindicatorHistoryTelesignal record, @Param("example") DeviceFaultindicatorHistoryTelesignalExample example);

    int updateByPrimaryKeySelective(DeviceFaultindicatorHistoryTelesignal record);

    int updateByPrimaryKey(DeviceFaultindicatorHistoryTelesignal record);
}