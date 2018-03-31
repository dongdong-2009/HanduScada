package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.BaseUser;
import main.com.handu.scada.db.bean.BaseUserExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface BaseUserMapper extends CommonMapper {
    long countByExample(BaseUserExample example);

    int deleteByExample(BaseUserExample example);

    int deleteByPrimaryKey(String userid);

    int insert(BaseUser record);

    int insertSelective(BaseUser record);

    List<BaseUser> selectByExample(BaseUserExample example);

    BaseUser selectByPrimaryKey(String userid);

    int updateByExampleSelective(@Param("tripEventRecord") BaseUser record, @Param("example") BaseUserExample example);

    int updateByExample(@Param("tripEventRecord") BaseUser record, @Param("example") BaseUserExample example);

    int updateByPrimaryKeySelective(BaseUser record);

    int updateByPrimaryKey(BaseUser record);
}