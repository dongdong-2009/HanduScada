package main.com.handu.scada.quartz.job.lp;

import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceTypeEnum;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.CommonJob;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2018/01/17.
 */
public class EveryDayOnceJob extends CommonJob implements BaseJob {

    private DeviceCmdTypeEnum[] cmdTypes = new DeviceCmdTypeEnum[]{
            /// <summary>
            /// 跳闸总次数
            /// </summary>
            DeviceCmdTypeEnum.TripTimes,
            /// <summary>
            /// 跳闸总次数
            /// </summary>
            DeviceCmdTypeEnum.ClosedTripTimes,
            /// <summary>
            /// 剩余电流保护跳闸次数
            /// </summary>
            DeviceCmdTypeEnum.ResidualTripTimes,
            /// <summary>
            /// 电流保护跳闸次数
            /// </summary>
            DeviceCmdTypeEnum.CurrentTripTimes,
            /// <summary>
            /// 电压保护跳闸次数
            /// </summary>
            DeviceCmdTypeEnum.VoltageTripTimes,
            /// <summary>
            /// 手动闭锁跳闸次数
            /// </summary>
            DeviceCmdTypeEnum.ManualClosingTripTimes,
            /// <summary>
            /// 缺零保护跳闸次数
            /// </summary>
            DeviceCmdTypeEnum.ZeroProtectionTrippingTimes,
            /// <summary>
            /// 试验跳闸次数（定时、远程,按键）
            /// </summary>
            DeviceCmdTypeEnum.TrippingTimes,
            /// <summary>
            /// 试验跳闸次数（定时、远程,按键）
            /// </summary>
            DeviceCmdTypeEnum.TrippingTimesModule, //----无解析
            /// <summary>
            /// 退出剩余电流保护次数
            /// </summary>
            DeviceCmdTypeEnum.ExitResidualModule,
            /// <summary>
            /// 保护器运行时间总累计
            /// </summary>
            DeviceCmdTypeEnum.RunTotalTime,
            /// <summary>
            /// 读剩余电流超限报警整定值
            /// </summary>
            //DeviceCmdTypeEnum.ReadResidualCurrentAlarmSettingValue,//----无解析
            /// <summary>
            /// 剩余电流整定参数块Residual current setting parameter block
            /// </summary>
            DeviceCmdTypeEnum.ResidualCurrentSettingParameterBlock,
            /// <summary>
            /// 电压整定参数块
            /// </summary>
            DeviceCmdTypeEnum.VoltageSettingParameterBlock,
            /// <summary>
            /// 电流整定参数块
            /// </summary>
            DeviceCmdTypeEnum.CurrentSettingParameterBlock
    };

    @Override
    public String jobName() {
        return "lp EveryDayOnceJob";
    }


    @Override
    public String cronExpression() {
        return "30 30 1 * * ?";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (enable) {
            LogUtils.error(DateUtils.dateToStr(DateUtils.getNowSqlDateTime()) + "-->" + jobName());
            send(DeviceTypeEnum.LP, cmdTypes);
        }
    }

    @Override
    public void isEnable(boolean isEnable) {
        enable = isEnable;
    }
}
