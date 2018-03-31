package main.com.handu.scada.protocol.protocol.DLT645;

import main.com.handu.scada.protocol.base.BaseIdentifyCodeDesc;
import main.com.handu.scada.protocol.enums.AnswerControlCodeEnum;
import main.com.handu.scada.protocol.enums.CodeSysEnum;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.protocol.DLT645.LP2007.DICodeLP2007;
import main.com.handu.scada.protocol.protocol.DLT645.LP2007.DataAnalyze;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 柳梦 on 2017/12/24.
 */
public class IdentifyCodeDesc extends BaseIdentifyCodeDesc {

    @Override
    public boolean parse() {
        if (controlCode != null && controlCode.getValue() == AnswerControlCodeEnum.ReadData.getValue()) {
            //电压值
            if (DICodeLP2007.equal(Dicode, DICodeLP2007.Voltage, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.Voltage);
                this.unit = "V";
                this.length = 6;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.Voltage;
                this.values = dataanalyze.getData(this);
            }
            //电流值
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.Current, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.Current);
                this.unit = "A";
                this.length = 9;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.Current;
                this.values = dataanalyze.getData(this);
            }

            //最大相 剩余电流值
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.Residual, true)) {
                ////this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.Residual);
                this.unit = ",mA";
                this.length = 3;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.ResidualAndMaxPhase;
                this.values = dataanalyze.getData(this);
            }

            //当前额度剩余电流动作值 极限不驱动时间
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.ResidualActionValue, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.ResidualActionValue);
                this.unit = "mA,ms";
                this.length = 4;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.ResidualActionValue;
                this.values = dataanalyze.getData(this);
            }

            //分合状态
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.RunState, true)) {
                dataanalyze.address = this.address;
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.RunState);
                this.unit = "";
                this.length = 1;
                this.codesystem = CodeSysEnum.BIN;
                this.cmdtype = DeviceCmdTypeEnum.RunState;
                this.values = dataanalyze.getData(this);
            }

            //设备上日期
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.RunDate, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.RunDate);
                this.unit = "年月日周";
                this.length = 4;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.RunDate;
                this.values = dataanalyze.getData(this);
            }

            //设备上时间
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.RunTime, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.RunTime);
                this.unit = "时分秒";
                this.length = 3;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.RunTime;
                this.values = dataanalyze.getData(this);
            }

            //事件记录
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.ProtectorTripEventRecord, true)) {
                dataanalyze.address = this.address;
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.ProtectorTripEventRecord);
                this.unit = "";
                this.length = 25;
                this.codesystem = CodeSysEnum.NONE;
                this.cmdtype = DeviceCmdTypeEnum.ProtectorTripEventRecord;
                this.values = dataanalyze.getData(this);
            }

            //控制字解析
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.ReadControlWordParameterModule, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.ReadControlWordParameterModule);
                this.unit = "";
                this.length = 25;
                this.codesystem = CodeSysEnum.NONE;
                this.cmdtype = DeviceCmdTypeEnum.ReadControlWordParameterModule;
                this.values = dataanalyze.getData(this);
            }

            //整定值解析
            //剩余电流整定
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.ResidualCurrentSettingParameterBlock, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.ResidualCurrentSettingParameterBlock);
                this.unit = "mA,mA,分";
                this.length = 4;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.ResidualCurrentSettingParameterBlock;
                this.values = dataanalyze.getData(this);
            }
            //电压整定
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.VoltageSettingParameterBlock, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.VoltageSettingParameterBlock);
                this.unit = "V";
                this.length = 6;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.VoltageSettingParameterBlock;
                this.values = dataanalyze.getData(this);
            }
            //电流整定
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.CurrentSettingParameterBlock, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.CurrentSettingParameterBlock);
                this.unit = "A,";
                this.length = 4;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.CurrentSettingParameterBlock;
                this.values = dataanalyze.getData(this);
            }

            //额定电流
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.RatedCurrent, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.RatedCurrent);
                this.unit = "A";
                this.length = 6;
                this.codesystem = CodeSysEnum.ASCII;
                this.cmdtype = DeviceCmdTypeEnum.RatedCurrent;
                this.values = dataanalyze.getData(this);
            }

            //额定电压
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.RatedVoltage, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.RatedVoltage);
                this.unit = "V";
                this.length = 6;
                this.codesystem = CodeSysEnum.ASCII;
                this.cmdtype = DeviceCmdTypeEnum.RatedVoltage;
                this.values = dataanalyze.getData(this);
            }

            //自动重合闸时间范围
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.AutoReclosingTimeRange, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.AutoReclosingTimeRange);
                this.unit = "";
                this.length = 24;
                this.codesystem = CodeSysEnum.ASCII;
                this.cmdtype = DeviceCmdTypeEnum.AutoReclosingTimeRange;
                this.values = dataanalyze.getData(this);
            }

            //设备型号
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.DeviceModel, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.ReadDeviceModel);
                this.unit = "";
                this.length = 10;
                this.codesystem = CodeSysEnum.ASCII;
                this.cmdtype = DeviceCmdTypeEnum.ReadDeviceModel;
                this.values = dataanalyze.getData(this);
            }

            //设备号
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.DeviceNumber, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.DeviceNumber);
                this.unit = "";
                this.length = 6;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.DeviceNumber;
                this.values = dataanalyze.getData(this);
            }

            //A型或AC型
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.AAC, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.AAC);
                this.unit = "";
                this.length = 24;
                this.codesystem = CodeSysEnum.ASCII;
                this.cmdtype = DeviceCmdTypeEnum.AAC;
                this.values = dataanalyze.getData(this);
            }

            //跳闸次数参模块
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.TripTimes, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.TripTimes);
                this.unit = "次";
                this.length = 16;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.TripTimes;
                this.values = dataanalyze.getData(this);
            }

            //生产日期
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.ProduceDate, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.ProduceDate);
                this.unit = "";
                this.length = 24;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.ProduceDate;
                this.values = dataanalyze.getData(this);
            }

            //退出剩余电流保护次数
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.ExitResidualModule, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.ExitResidualModule);
                this.unit = "次";
                this.length = 2;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.ExitResidualModule;
                this.values = dataanalyze.getData(this);
            }

            //壳架电流
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MaxShellCurrent, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.MaxShellCurrent);
                this.unit = "";
                this.length = 6;
                this.codesystem = CodeSysEnum.ASCII;
                this.cmdtype = DeviceCmdTypeEnum.MaxShellCurrent;
                this.values = dataanalyze.getData(this);
            }

            //保护器运行时间总累计
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.RunTotalTime, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.RunTotalTime);
                this.unit = "分";
                this.length = 4;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.RunTotalTime;
                this.values = dataanalyze.getData(this);
            }

            //厂家硬件版本号
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.HardWareVersionNumber, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.HardWareVersionNumber);
                this.unit = "";
                this.length = 32;
                this.codesystem = CodeSysEnum.ASCII;
                this.cmdtype = DeviceCmdTypeEnum.HardWareVersionNumber;
                this.values = dataanalyze.getData(this);
            }

            //厂家固件 版本号
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.FirmwareVersionNumber, true)
                    || DICodeLP2007.equal(Dicode, DICodeLP2007.FirmwareVersionNumber1, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.FirmwareVersionNumber);
                this.unit = "";
                this.length = 32;
                this.codesystem = CodeSysEnum.ASCII;
                this.cmdtype = DeviceCmdTypeEnum.FirmwareVersionNumber;
                this.values = dataanalyze.getData(this);
            }

            //协议版本号
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.ProtocolVerNumber, true)) {
                //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.ProtocolVerNumber);
                this.unit = "";
                this.length = 16;
                this.codesystem = CodeSysEnum.ASCII;
                this.cmdtype = DeviceCmdTypeEnum.ProtocolVerNumber;
                this.values = dataanalyze.getData(this);
            }

            //读波特率
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.CommunicationBaudRateCharacter, true)) {
                this.unit = "";
                this.length = 1;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.CommunicationBaudRateCharacter;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            }

            //最值记录
            else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MaxRC, true)) {
                this.unit = ",mA,";
                this.length = 9;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MaxRC;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MinRC, true)) {
                this.unit = ",mA,";
                this.length = 9;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MinRC;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MaxAC, true)) {
                this.unit = "A,";
                this.length = 9;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MaxAC;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MaxBC, true)) {
                this.unit = "A,";
                this.length = 9;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MaxBC;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MaxCC, true)) {
                this.unit = "A,";
                this.length = 9;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MaxCC;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MinAC, true)) {
                this.unit = "A,";
                this.length = 9;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MinAC;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MinBC, true)) {
                this.unit = "A,";
                this.length = 9;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MinBC;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MinCC, true)) {
                this.unit = "A,";
                this.length = 9;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MinCC;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MaxAV, true)) {
                this.unit = "V,";
                this.length = 8;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MaxAV;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MaxBV, true)) {
                this.unit = "V,";
                this.length = 8;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MaxBV;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MaxCV, true)) {
                this.unit = "V,";
                this.length = 8;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MaxCV;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MinAV, true)) {
                this.unit = "V,";
                this.length = 8;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MinAV;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MinBV, true)) {
                this.unit = "V,";
                this.length = 8;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MinBV;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.MinCV, true)) {
                this.unit = "V,";
                this.length = 8;
                this.codesystem = CodeSysEnum.BCD;
                this.cmdtype = DeviceCmdTypeEnum.MinCV;
                //this.name = EnumHelper.GetDescription(this.cmdtype);
                this.values = dataanalyze.getData(this);
            } else if (DICodeLP2007.equal(Dicode, DICodeLP2007.SecondHourData, true)) {
                parseSecondLpData(this.data);
            } else {
                result = false;
            }
        } else if (controlCode == AnswerControlCodeEnum.ReadAddress) {
            //this.name = EnumHelper.GetDescription(DeviceCmdTypeEnum.ReadPostalAddress);
            this.unit = "";
            this.length = 6;
            this.codesystem = CodeSysEnum.NONE;
            this.cmdtype = DeviceCmdTypeEnum.ReadPostalAddress;
            this.values = dataanalyze.getData(this);
        } else if (controlCode == AnswerControlCodeEnum.WriteAddress) {

        } else if (controlCode == AnswerControlCodeEnum.ChangeRate) {
            //this.name = EnumHelper.GetDescription(controlCode);
            this.unit = "";
            this.length = 1;
            this.codesystem = CodeSysEnum.BCD;
            this.cmdtype = DeviceCmdTypeEnum.CommunicationBaudRateCharacter;
            this.values = dataanalyze.getData(this);
        } else if (controlCode == AnswerControlCodeEnum.WriteData) {
        } else if (controlCode == AnswerControlCodeEnum.ControlCmd) {
            //this.name = EnumHelper.GetDescription(controlCode);
            this.unit = "";
            this.length = 0;
            this.codesystem = CodeSysEnum.BCD;
            this.cmdtype = DeviceCmdTypeEnum.RemoteOPT;
            this.values = dataanalyze.getData(this);
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public boolean isCorrectAnswer(byte controlCode) {
        //是否正常应答
        if (controlCode >= (byte) 0x91 && controlCode <= (byte) 0x9c) {
            this.controlCode = AnswerControlCodeEnum.getControlCode(controlCode);
            if (this.controlCode != null) {
                byte a = (byte) this.controlCode.getValue();
                return a == controlCode;
            }
        }
        return false;
    }

    public IdentifyCodeDesc() {
        dataanalyze = new DataAnalyze();
    }

    private void parseSecondLpData(byte[] bytes) {
        if (bytes == null || bytes.length == 0 || bytes.length % 35 != 0) return;
        this.secondValues = new ArrayList<>();

        //上报时间
        byte[] data = new byte[6];
        System.arraycopy(bytes, 0, data, 0, data.length);
        this.data = data;
        this.unit = "年月日时分秒";
        this.length = 6;
        this.codesystem = CodeSysEnum.BCD;
        this.cmdtype = DeviceCmdTypeEnum.SecondLpUploadDateTime;
        List<DataAttr> dataAttrs = dataanalyze.getData(this);
        this.secondValues.add(dataAttrs);

        //采样时间
        data = new byte[6];
        System.arraycopy(bytes, 12, data, 0, data.length);
        this.data = data;
        this.unit = "年月日时分秒";
        this.length = 6;
        this.codesystem = CodeSysEnum.BCD;
        this.cmdtype = DeviceCmdTypeEnum.SecondLpCollectDateTime;
        dataAttrs = dataanalyze.getData(this);
        this.secondValues.add(dataAttrs);

        //电压
        data = new byte[6];
        System.arraycopy(bytes, 18, data, 0, data.length);
        this.data = data;
        this.unit = "V";
        this.length = 6;
        this.codesystem = CodeSysEnum.BCD;
        this.cmdtype = DeviceCmdTypeEnum.Voltage;
        dataAttrs = dataanalyze.getData(this);
        this.secondValues.add(dataAttrs);

        //电流
        data = new byte[9];
        System.arraycopy(bytes, 24, data, 0, data.length);
        this.data = data;
        this.unit = "A";
        this.length = 9;
        this.codesystem = CodeSysEnum.BCD;
        this.cmdtype = DeviceCmdTypeEnum.Current;
        dataAttrs = dataanalyze.getData(this);
        this.secondValues.add(dataAttrs);

        //剩余电流
        data = new byte[2];
        System.arraycopy(bytes, 24, data, 0, data.length);
        this.data = data;
        this.unit = "mA";
        this.length = 2;
        this.codesystem = CodeSysEnum.BCD;
        this.cmdtype = DeviceCmdTypeEnum.Residual;
        dataAttrs = dataanalyze.getData(this);
        this.secondValues.add(dataAttrs);
    }
}
