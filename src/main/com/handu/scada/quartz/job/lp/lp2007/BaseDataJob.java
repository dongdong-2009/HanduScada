package main.com.handu.scada.quartz.job.lp.lp2007;

import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.BaseDtuCommand;
import main.com.handu.scada.utils.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2018/01/02.
 */
public class BaseDataJob extends BaseDtuCommand implements BaseJob {

    private DeviceCmdTypeEnum[] cmdTypes = new DeviceCmdTypeEnum[]{
            DeviceCmdTypeEnum.Voltage,
            DeviceCmdTypeEnum.Current,
            DeviceCmdTypeEnum.ResidualAndMaxPhase
    };

    @Override
    public String jobName() {
        return "lp2007 BaseDataJob";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        send(DeviceTypeEnum.LP2007, cmdTypes);
    }

    @Override
    public String cronExpression() {
        return "0 0/5 * * * ?";
    }
}
