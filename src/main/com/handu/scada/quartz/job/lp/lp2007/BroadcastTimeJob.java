package main.com.handu.scada.quartz.job.lp.lp2007;

import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.CommonJob;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2018/03/02.
 */
public class BroadcastTimeJob extends CommonJob implements BaseJob {

    /// <summary>
    /// 广播校时
    /// </summary>
    private DeviceCmdTypeEnum cmdTypeEnum = DeviceCmdTypeEnum.BroadcastTime;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            LogUtils.error(DateUtils.dateToStr(DateUtils.getNowSqlDateTime()) + "-->" + jobName());
            send(DeviceTypeEnum.LP2007, cmdTypeEnum);
            send(DeviceTypeEnum.SECOND_LP2007, cmdTypeEnum);
    }

    @Override
    public String jobName() {
        return "lp2007 BroadcastTimeJob";
    }

    @Override
    public String cronExpression() {
        //默认每个小时两次对时(整点和半点)
        return "0 0,30 * * * ?";
    }
}
