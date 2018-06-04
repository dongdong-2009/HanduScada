package main.com.handu.scada.quartz.job.falling_type_switch;

import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.quartz.job.BaseDtuCommand;
import main.com.handu.scada.quartz.job.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2018/03/14.
 */
public class AllCallJob extends BaseDtuCommand implements BaseJob {

    private DeviceCmdTypeEnum cmdType = DeviceCmdTypeEnum.ALL_CALL;

    @Override
    public boolean isEnable() {
        return false;
    }

    @Override
    public String jobName() {
        return "fall switch AllCallJob";
    }

    @Override
    public String cronExpression() {
        return "50 4/5 * * * ?";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        send(DeviceTypeEnum.CY_FALL_TYPE_SWITCH, cmdType);
    }
}
