package main.com.handu.scada.quartz.job.lp.lp2007;

import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.BaseDtuCommand;
import main.com.handu.scada.utils.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2017/12/08.
 */
public class DateTimeJob extends BaseDtuCommand implements BaseJob {

    private DeviceCmdTypeEnum[] cmdTypes = new DeviceCmdTypeEnum[]{
            DeviceCmdTypeEnum.RunDate,
            DeviceCmdTypeEnum.RunTime
    };


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        send(DeviceTypeEnum.LP2007, cmdTypes);
    }

    @Override
    public String cronExpression() {
        //整点采集
        return "0 0 * * * ?";
    }

    @Override
    public String jobName() {
        return "lp2007 DateTimeJob";
    }
}
