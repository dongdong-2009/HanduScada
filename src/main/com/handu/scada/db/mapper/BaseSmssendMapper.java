package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.BaseSmssend;
import main.com.handu.scada.db.bean.BaseSmssendExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface BaseSmssendMapper extends CommonMapper {
    long countByExample(BaseSmssendExample example);

    int deleteByExample(BaseSmssendExample example);

    int deleteByPrimaryKey(String oid);

    int insert(BaseSmssend record);

    int insertSelective(BaseSmssend record);

    List<BaseSmssend> selectByExample(BaseSmssendExample example);

    BaseSmssend selectByPrimaryKey(String oid);

    int updateByExampleSelective(@Param("record") BaseSmssend record, @Param("example") BaseSmssendExample example);

    int updateByExample(@Param("record") BaseSmssend record, @Param("example") BaseSmssendExample example);

    int updateByPrimaryKeySelective(BaseSmssend record);

    int updateByPrimaryKey(BaseSmssend record);
}