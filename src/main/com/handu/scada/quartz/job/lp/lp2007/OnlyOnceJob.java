package main.com.handu.scada.quartz.job.lp.lp2007;

import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.quartz.job.BaseJob;
import main.com.handu.scada.quartz.job.BaseDtuCommand;
import main.com.handu.scada.utils.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 柳梦 on 2018/01/17.
 */
public class OnlyOnceJob extends BaseDtuCommand implements BaseJob {

    private DeviceCmdTypeEnum[] cmdTypes = new DeviceCmdTypeEnum[]{
            /// <summary>
            /// 剩余电流动作值，当前额定极限不驱动时间
            /// </summary>
            DeviceCmdTypeEnum.ResidualActionValue,
            /// <summary>
            /// 额定电压
            /// </summary>
            DeviceCmdTypeEnum.RatedVoltage,
            /// <summary>
            /// 额定电流
            /// </summary>
            DeviceCmdTypeEnum.RatedCurrent,
            /// <summary>
            /// 自动重合闸时间范围
            /// </summary>
            DeviceCmdTypeEnum.AutoReclosingTimeRange,
            /// <summary>
            /// 通信波特率征字（ 0-9）
            /// </summary>
            DeviceCmdTypeEnum.CommunicationBaudRateCharacter,
            /// <summary>
            /// 通信波特率征字（ 0-9）
            /// </summary>
            DeviceCmdTypeEnum.WriteCommunicationBaudRateCharacter,//----无解析
            /// <summary>
            /// 最大（ 壳架 ）电流 (Inm)
            /// </summary>
            DeviceCmdTypeEnum.MaxShellCurrent,
            /// <summary>
            /// 生产日期
            /// </summary>
            DeviceCmdTypeEnum.ProduceDate,
            /// <summary>
            /// 协议版本号
            /// </summary>
            DeviceCmdTypeEnum.ProtocolVerNumber,
            /// <summary>
            /// 厂家固件版本号
            /// </summary>
            DeviceCmdTypeEnum.FirmwareVersionNumber,
            /// <summary>
            /// 厂家硬件版本号
            /// </summary>
            DeviceCmdTypeEnum.HardWareVersionNumber,
            /// <summary>
            ///剩余电流动作特性（ A型或 AC 型）
            /// </summary>
            DeviceCmdTypeEnum.AAC,
            /// <summary>
            /// 读设备型号
            /// </summary>
            DeviceCmdTypeEnum.ReadDeviceModel,
    };

    @Override
    public String jobName() {
        return "lp2007 OnlyOnceJob";
    }

    @Override
    public String cronExpression() {
        //表示只执行一次
        return "30";
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        send(DeviceTypeEnum.LP2007, cmdTypes);
    }
}
