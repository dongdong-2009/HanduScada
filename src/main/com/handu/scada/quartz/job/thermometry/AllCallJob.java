package main.com.handu.scada.quartz.job.thermometry;

import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.CommonJob;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2018/03/14.
 */
public class AllCallJob extends CommonJob implements BaseJob {

    private DeviceCmdTypeEnum cmdType = DeviceCmdTypeEnum.ALL_CALL;

    @Override
    public boolean isEnable() {
        return false;
    }

    @Override
    public String jobName() {
        return "thermometry AllCallJob";
    }

    @Override
    public String cronExpression() {
        return "50 4/5 * * * ?";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LogUtils.error(DateUtils.dateToStr(DateUtils.getNowSqlDateTime()) + "-->" + jobName());
        send(DeviceTypeEnum.WIRED_TEMPERATURE, cmdType);
    }
}
