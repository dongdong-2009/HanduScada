package main.com.handu.scada.db.mapper;

import java.util.List;
import main.com.handu.scada.db.bean.QrtzCronTriggers;
import main.com.handu.scada.db.bean.QrtzCronTriggersExample;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import org.apache.ibatis.annotations.Param;

public interface QrtzCronTriggersMapper extends CommonMapper {
    long countByExample(QrtzCronTriggersExample example);

    int deleteByExample(QrtzCronTriggersExample example);

    int deleteByPrimaryKey(@Param("SCHED_NAME") String SCHED_NAME, @Param("TRIGGER_NAME") String TRIGGER_NAME, @Param("TRIGGER_GROUP") String TRIGGER_GROUP);

    int insert(QrtzCronTriggers record);

    int insertSelective(QrtzCronTriggers record);

    List<QrtzCronTriggers> selectByExample(QrtzCronTriggersExample example);

    QrtzCronTriggers selectByPrimaryKey(@Param("SCHED_NAME") String SCHED_NAME, @Param("TRIGGER_NAME") String TRIGGER_NAME, @Param("TRIGGER_GROUP") String TRIGGER_GROUP);

    int updateByExampleSelective(@Param("tripEventRecord") QrtzCronTriggers record, @Param("example") QrtzCronTriggersExample example);

    int updateByExample(@Param("tripEventRecord") QrtzCronTriggers record, @Param("example") QrtzCronTriggersExample example);

    int updateByPrimaryKeySelective(QrtzCronTriggers record);

    int updateByPrimaryKey(QrtzCronTriggers record);
}