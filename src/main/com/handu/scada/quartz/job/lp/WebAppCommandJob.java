package main.com.handu.scada.quartz.job.lp;

import main.com.handu.scada.db.bean.BaseRequestdata;
import main.com.handu.scada.db.bean.BaseRequestdataExample;
import main.com.handu.scada.db.mapper.BaseRequestdataMapper;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.event.EventManager;
import main.com.handu.scada.event.events.DownProtocolEvent;
import main.com.handu.scada.netty.client.dtu.MsgPriority;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.BaseDtuCommand;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.StringsUtils;
import main.com.handu.scada.utils.UUIDUtils;
import org.apache.ibatis.session.SqlSession;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

/**
 * Created by 柳梦 on 2018/01/03.
 */
public class WebAppCommandJob extends BaseDtuCommand implements BaseJob {

    private SqlSession sqlSession;

    @Override
    public String jobName() {
        return "WebAppCommandJob";
    }

    @Override
    public String cronExpression() {
        return "0/30 * * * * ?";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            sqlSession = MyBatisUtil.getSqlSession(false);
            BaseRequestdataMapper mapper = sqlSession.getMapper(BaseRequestdataMapper.class);
            BaseRequestdataExample example = new BaseRequestdataExample();
            example.setOrderByClause(" RecordTime ASC ");
            List<BaseRequestdata> requestdataList = mapper.selectByExample(example);
            if (requestdataList != null && requestdataList.size() > 0) {
                requestdataList
                        .stream()
                        .filter(requestdata -> requestdata.getCmdtype() != null)
                        .forEach(requestdata -> {
                            DeviceCmdTypeEnum cmdTypeEnum = DeviceCmdTypeEnum.findDeviceCmdType(requestdata.getCmdtype());
                            if (cmdTypeEnum != null) {
                                //下发
                                ProtocolLayerData protocolLayerData = new ProtocolLayerData() {
                                    {
                                        DTUString = requestdata.getDtuaddress();
                                        deviceTypeEnum = DeviceTypeEnum.LP2007;
                                        PostalAddress = requestdata.getDeviceaddress();
                                        HasDTUHead = true;
                                        CmdType = cmdTypeEnum;
                                        strData = requestdata.getInfo();
                                    }
                                };
                                EventManager.getInstance().publish(new DownProtocolEvent(MsgPriority.HIGH, protocolLayerData), MsgPriority.HIGH);
                                if (cmdTypeEnum.name().startsWith("Write")) {
                                    String cmdType = null;
                                    if (requestdata.getCmdtype().startsWith(DeviceCmdTypeEnum.WriteControlWordParameterModule.name())) {
                                        cmdType = DeviceCmdTypeEnum.ReadControlWordParameterModule.name();
                                    } else if (requestdata.getCmdtype().equals(DeviceCmdTypeEnum.WriteUndervoltagesettingvalue.name())
                                            || requestdata.getCmdtype().equals(DeviceCmdTypeEnum.WriteOvervoltagesettingvalue.name())
                                            || requestdata.getCmdtype().equals(DeviceCmdTypeEnum.Writephasebreakvoltagesettingvalue.name())) {
                                        cmdType = DeviceCmdTypeEnum.VoltageSettingParameterBlock.name();
                                    } else if (requestdata.getCmdtype().equals(DeviceCmdTypeEnum.WriteCurrentoverloadwarningsettingvalue.name())) {
                                        cmdType = DeviceCmdTypeEnum.CurrentSettingParameterBlock.name();
                                    } else if (requestdata.getCmdtype().equals(DeviceCmdTypeEnum.WriteResidualCurrentAlarmSettingValue.name())) {
                                        cmdType = DeviceCmdTypeEnum.ResidualCurrentSettingParameterBlock.name();
                                    }
                                    if (!StringsUtils.isEmpty(cmdType)) {
                                        BaseRequestdata baseData = new BaseRequestdata();
                                        baseData.setCmdtype(cmdType);
                                        baseData.setDtuaddress(requestdata.getDtuaddress());
                                        baseData.setDeviceaddress(requestdata.getDeviceaddress());
                                        baseData.setTabid(requestdata.getTabid());
                                        baseData.setTabname(requestdata.getTabname());
                                        baseData.setDatafrom("Scada");
                                        baseData.setRecordtime(DateUtils.getNowSqlDateTime());
                                        baseData.setState(0);
                                        baseData.setOid(UUIDUtils.getUUId());
                                        mapper.insert(baseData);
                                    }
                                }
                            }
                            //删除该条记录
                            mapper.deleteByPrimaryKey(requestdata.getOid());
                        });
            }
            if (sqlSession != null) sqlSession.commit(true);
        } catch (Exception e) {
            if (sqlSession != null) sqlSession.rollback(true);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    @Override
    public boolean isEnable() {
        return false;
    }
}
