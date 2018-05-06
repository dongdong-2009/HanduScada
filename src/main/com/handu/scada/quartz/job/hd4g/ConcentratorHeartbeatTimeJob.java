package main.com.handu.scada.quartz.job.hd4g;

import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.CommonJob;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2018/04/09.
 * 集中器最后一次心跳时间
 */
public class ConcentratorHeartbeatTimeJob extends CommonJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LogUtils.error(DateUtils.dateToStr(DateUtils.getNowSqlDateTime()) + "-->" + jobName());
        sendTo4g(DeviceCmdTypeEnum.ConcentratorHeartbeatTime);
    }

    @Override
    public boolean isEnable() {
        return false;
    }

    @Override
    public String jobName() {
        return "4g ConcentratorHeartbeatTimeJob";
    }

    @Override
    public String cronExpression() {
        return "50 0/15 * * * ?";
    }
}
