package main.com.handu.scada.quartz.job.gdw3761;

import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.CommonJob;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2018/04/27.
 */
public class BaseDataJob extends CommonJob implements BaseJob {

    private DeviceCmdTypeEnum cmdType = DeviceCmdTypeEnum.HM_AFN0C25;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LogUtils.error(DateUtils.dateToStr(DateUtils.getNowSqlDateTime()) + "-->" + jobName());
        sendTo4g(cmdType);
    }

    @Override
    public String jobName() {
        return "AFN0C25 BaseDataJob";
    }

    @Override
    public String cronExpression() {
        return "40 3/5 * * * ?";
    }
}
