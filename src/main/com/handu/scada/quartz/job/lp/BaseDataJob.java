package main.com.handu.scada.quartz.job.lp;

import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceTypeEnum;
import main.com.handu.scada.quartz.job.CommonJob;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2018/01/02.
 */
public class BaseDataJob extends CommonJob implements BaseJob {

    private DeviceCmdTypeEnum[] cmdTypes = new DeviceCmdTypeEnum[]{
            DeviceCmdTypeEnum.Voltage,
            DeviceCmdTypeEnum.Current,
            DeviceCmdTypeEnum.ResidualAndMaxPhase
    };


    @Override
    public void isEnable(boolean isEnable) {
        enable = isEnable;
    }

    @Override
    public String jobName() {
        return "lp BaseDataJob";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (enable) {
            LogUtils.error(DateUtils.dateToStr(DateUtils.getNowSqlDateTime()) + "-->" + jobName());
            send(DeviceTypeEnum.LP, cmdTypes);
        }
    }

    @Override
    public String cronExpression() {
        return "0 0/5 * * * ?";
    }
}
