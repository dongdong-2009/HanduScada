package main.com.handu.scada.quartz.job.protocol101;

import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol101.protocol.enums.Protocol101CmdEnum;
import main.com.handu.scada.quartz.job.Base101Command;
import main.com.handu.scada.quartz.job.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2018/05/22.
 */
public class AllCallJob extends Base101Command implements BaseJob {

    @Override
    public String jobName() {
        return "protocol101 AllCall";
    }

    @Override
    public String cronExpression() {
        return "20 2/5 * * * ?";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        send(DeviceTypeEnum.HC_FAULT_INDICATOR, Protocol101CmdEnum.ALL_CALL);
    }
}
