package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.BaseRequestdata;
import main.com.handu.scada.db.bean.BaseRequestdataExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface BaseRequestdataMapper extends CommonMapper {
    long countByExample(BaseRequestdataExample example);

    int deleteByExample(BaseRequestdataExample example);

    int deleteByPrimaryKey(String oid);

    int insert(BaseRequestdata record);

    int insertSelective(BaseRequestdata record);

    List<BaseRequestdata> selectByExample(BaseRequestdataExample example);

    BaseRequestdata selectByPrimaryKey(String oid);

    int updateByExampleSelective(@Param("tripEventRecord") BaseRequestdata record, @Param("example") BaseRequestdataExample example);

    int updateByExample(@Param("tripEventRecord") BaseRequestdata record, @Param("example") BaseRequestdataExample example);

    int updateByPrimaryKeySelective(BaseRequestdata record);

    int updateByPrimaryKey(BaseRequestdata record);
}