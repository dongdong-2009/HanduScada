package main.com.handu.scada.protocol.protocol.DLT645.LP1997;

import main.com.handu.scada.protocol.base.DownStreamDLT645;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.StringsUtils;

import java.util.Calendar;

/**
 * Created by 柳梦 on 2018/04/24.
 */
public class DownStreamDLT6451997 extends DownStreamDLT645 {


    public DownStreamDLT6451997(DeviceCmdTypeEnum cmdTypeEnum) {
        init(cmdTypeEnum);
    }

    private void init(DeviceCmdTypeEnum cmdType) {

        switch (cmdType) {
            //三相电压电流和剩余电流
            case VoltageCurrentAndResidualCurrent:
                this.controlCode = 0x01;
                this.length = 0x02;
                this.diCode = DICodeLP1997.increase33(DICodeLP1997.VoltageCurrentAndResidualCurrent);
                break;

            //读时钟
            case ReadClock:
                this.controlCode = 0x01;
                this.length = 0x02;
                this.diCode = DICodeLP1997.increase33(DICodeLP1997.ReadClock);
                break;

            //校对时钟
            case BroadcastTime:
                this.controlCode = 0x04;
                this.length = 0x09;
                this.diCode = DICodeLP1997.increase33(DICodeLP1997.BroadcastTime);
                Calendar calendar = Calendar.getInstance();
                datas = new byte[6];
                datas[0] = HexUtils.increase33(HexUtils.intToBCDByte(calendar.get(Calendar.SECOND)));
                datas[1] = HexUtils.increase33(HexUtils.intToBCDByte(calendar.get(Calendar.MINUTE)));
                datas[2] = HexUtils.increase33(HexUtils.intToBCDByte(calendar.get(Calendar.HOUR)));
                datas[3] = HexUtils.increase33(HexUtils.intToBCDByte(calendar.get(Calendar.DAY_OF_MONTH)));
                datas[4] = HexUtils.increase33(HexUtils.intToBCDByte(calendar.get(Calendar.MONTH) + 1));
                datas[5] = HexUtils.increase33(HexUtils.intToBCDByte(calendar.get(Calendar.YEAR)));
                break;

            //读设备地址
            case ReadPostalAddress:
                this.controlCode = 0x01;
                this.length = 0x02;
                this.diCode = DICodeLP1997.increase33(DICodeLP1997.ReadPostalAddress);
                break;

            //写设备地址
            case WritePostalAddress:
                this.controlCode = 0x04;
                this.length = 0x08;
                this.diCode = DICodeLP1997.increase33(DICodeLP1997.WritePostalAddress);
                this.datas = HexUtils.stringToBytes(StringsUtils.padLeft(this.address, 12, "0"));
                break;

            //合闸
            case ExecuteON:
                this.controlCode = 0x04;
                this.length = 0x03;
                this.diCode = DICodeLP1997.increase33(DICodeLP1997.ExecuteON);
                this.datas = new byte[]{0x5f};
                break;

            //分闸
            case ExecuteOFF:
                this.controlCode = 0x04;
                this.length = 0x03;
                this.diCode = DICodeLP1997.increase33(DICodeLP1997.ExecuteOFF);
                this.datas = new byte[]{0x50};
                break;

            //开关试跳
            case SwitchTrip:
                this.controlCode = 0x04;
                this.length = 0x02;
                this.diCode = DICodeLP1997.increase33(DICodeLP1997.SwitchTrip);
                break;

            //读当前开关信息及动作值和时间
            case RunState:
                this.controlCode = 0x01;
                this.length = 0x02;
                this.diCode = DICodeLP1997.increase33(DICodeLP1997.RunState);
                break;

            //读参数
            case ReadControlWordParameterModule:
                this.controlCode = 0x01;
                this.length = 0x02;
                this.diCode = DICodeLP1997.increase33(DICodeLP1997.ReadControlWordParameterModule);
                break;

            //写参数
            //case WriteControlWordParameterModule:
            //this.controlCode = 0x04;
            //this.length = (byte) (0x02 + DownStreamDLT6451997.WriteParameterDownstream.Length);
            //this.diCode = DICodeLP1997.WriteControlWordParameterModule;
            //this.datas = DownStreamDLT6451997.WriteParameterDownstream;
            //break;

            //读最近第1次跳闸类型动作值及时间
            case ProtectorTripEventRecord:
                this.controlCode = 0x01;
                this.length = 0x02;
                this.diCode = DICodeLP1997.increase33(DICodeLP1997.ProtectorTripEventRecord);
                break;
            default:
                break;
        }
    }

    @Override
    public void GetBytes() {
        if (!super.getBytes()) {
            cmdByte = null;
        }
    }
}
