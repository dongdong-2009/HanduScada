package main.com.handu.scada.quartz.job.lp.lp1997;

import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.CommonJob;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2018/04/26.
 */
public class BaseDataJob extends CommonJob implements BaseJob {

    @Override
    public String jobName() {
        return "lp1997 BaseDataJob";
    }

    @Override
    public String cronExpression() {
        return "0 0/5 * * * ?";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LogUtils.error(DateUtils.dateToStr(DateUtils.getNowSqlDateTime()) + "-->" + jobName());
        send(DeviceTypeEnum.LP1997, DeviceCmdTypeEnum.VoltageCurrentAndResidualCurrent);
    }
}
