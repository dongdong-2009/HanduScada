package main.com.handu.scada.quartz.job.lp.lp1997;

import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.quartz.job.BaseDtuCommand;
import main.com.handu.scada.quartz.job.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2017/12/08.
 */
public class DateTimeJob extends BaseDtuCommand implements BaseJob {

    private DeviceCmdTypeEnum[] cmdTypes = new DeviceCmdTypeEnum[]{
            DeviceCmdTypeEnum.ReadClock
    };


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        send(DeviceTypeEnum.LP1997, cmdTypes);
    }

    @Override
    public String cronExpression() {
        //整点采集
        return "0 0 * * * ?";
    }

    @Override
    public String jobName() {
        return "lp1997 DateTimeJob";
    }
}
