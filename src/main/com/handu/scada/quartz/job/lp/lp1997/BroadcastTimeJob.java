package main.com.handu.scada.quartz.job.lp.lp1997;

import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.quartz.job.BaseDtuCommand;
import main.com.handu.scada.quartz.job.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2018/03/02.
 */
public class BroadcastTimeJob extends BaseDtuCommand implements BaseJob {

    //广播校时
    private DeviceCmdTypeEnum cmdTypeEnum = DeviceCmdTypeEnum.BroadcastTime;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        send(DeviceTypeEnum.LP1997, cmdTypeEnum);
    }

    @Override
    public String jobName() {
        return "lp1997 BroadcastTimeJob";
    }

    @Override
    public String cronExpression() {
        //默认每个小时两次对时(整点和半点)
        return "0 0,30 * * * ?";
    }
}
