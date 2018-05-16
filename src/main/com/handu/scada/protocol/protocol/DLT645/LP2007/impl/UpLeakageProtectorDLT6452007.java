package main.com.handu.scada.protocol.protocol.DLT645.LP2007.impl;

import main.com.handu.scada.enums.DeviceTableEnum;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuUpParse;
import main.com.handu.scada.protocol.base.BaseDLT645;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.protocol.DLT645.LP2007.IdentifyCodeDesc;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.StringsUtils;

/**
 * Created by 柳梦 on 2017/12/24.
 */

@DtuUpParse
public class UpLeakageProtectorDLT6452007 extends BaseDLT645 {

    public UpLeakageProtectorDLT6452007() {
    }

    @Override
    public void getAddress(byte[] buff) {
        if (buff != null) {
            if (buff.length >= 7) {
                ///报文结构解析
                this.AddressCode = new byte[6];
                System.arraycopy(buff, 1, this.AddressCode, 0, this.AddressCode.length);
                this.PostalAddress = HexUtils.byteArrayToHexStr(HexUtils.reverse(this.AddressCode));
                if (StringsUtils.padLeft(this.PostalAddress, 12, "0").equals("000000000000")) {
                    this.PostalAddress = StringsUtils.padLeft(this.dtuAddress, 12, "0");
                } else if (StringsUtils.padLeft(this.PostalAddress, 12, "0").equals("000000000001")) {
                    this.PostalAddress = StringsUtils.padLeft((Integer.parseInt(this.dtuAddress) + 1), 12, "0");
                }
            }
        }
    }

    @Override
    public boolean valid(byte[] buff) {
        try {
            if (buff.length < 10) return false;
            //漏保以0x68开头，中间6位地址，加上0x68，最后以0x16结束
            if (buff[0] == (byte) 0x68 && buff[7] == 0x68 && buff[buff.length - 1] == 0x16) {
                byte blen = (byte) (buff[9] + (byte) 0x0c);
                if (blen != buff.length) return false;//长度校验
                byte cs = 0;
                int len = buff.length - 2;
                //针头针尾校验，
                for (int i = 0; i < len; i++)//有10+Length个字节需要进行校验
                {
                    cs += buff[i];//透传的帧从第5个字节开始
                }
                byte controlCode = buff[8];
                //校验和,并且应答满足国标协议
                return cs == buff[len] && ((controlCode >= (byte) 0x91 && controlCode <= (byte) 0x9c)
                        || (controlCode >= (byte) 0xD1 && controlCode <= (byte) 0xDC));
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return false;
    }

    @Override
    public ProtocolLayerData parse(MediaData mediaData) {
        try {
            byte[] buff = mediaData.CommandData;
            if (buff == null) return null;
            //漏报报文以0xFE开头0x16结束,去除前导字节
            buff = HexUtils.cleanFEAndFF(buff);
            if (valid(buff)) {
                dtuAddress = mediaData.DTUString;
                getAddress(buff);
                ///报文结构解析
                this.AddressCode = new byte[6];
                System.arraycopy(buff, 1, this.AddressCode, 0, this.AddressCode.length);
                this.ControlCode = buff[8];
                this.DataLength = buff[9];
                this.CheckCode = buff[buff.length - 2];
                //非异常应答
                if (!(ControlCode >= (byte) 0xD1 && ControlCode <= (byte) 0xDC)) {
                    if (this.ControlCode == (byte) 0x93
                            || this.ControlCode == (byte) 0x94
                            || this.ControlCode == (byte) 0x95
                            || this.ControlCode == (byte) 0x97
                            || this.ControlCode == (byte) 0x98
                            || this.ControlCode == (byte) 0x9C) {
                        this.Datas = new byte[this.DataLength];
                        System.arraycopy(buff, 10, this.Datas, 0, this.DataLength);
                    } else {
                        this.IdentifyCode = new byte[4];
                        System.arraycopy(buff, 10, this.IdentifyCode, 0, this.IdentifyCode.length);
                        this.Datas = new byte[this.DataLength - 4];
                        System.arraycopy(buff, 14, this.Datas, 0, (this.DataLength - 4));
                    }
                }
                ///数据域解析
                this.identifyCodeDesc = new IdentifyCodeDesc();
                if (identifyCodeDesc.isCorrectAnswer(this.ControlCode)) {
                    this.identifyCodeDesc.DiCode = this.IdentifyCode;
                    this.identifyCodeDesc.data = this.Datas;
                    this.identifyCodeDesc.sourceData = buff;
                    this.identifyCodeDesc.address = this.PostalAddress;
                    this.identifyCodeDesc.dTime = DateUtils.getNowSqlDateTime();
                    this.IsSuccess = identifyCodeDesc.parse();
                }
                if (IsSuccess) {
                    return new ProtocolLayerData() {{
                        deviceTypeEnum = DeviceTypeEnum.LP2007;
                        CommandData = mediaData.CommandData;
                        CommandName = mediaData.MsgName;
                        DLT645Address = UpLeakageProtectorDLT6452007.this.AddressCode;
                        PostalAddress = UpLeakageProtectorDLT6452007.this.PostalAddress;
                        DTUString = mediaData.DTUString;
                        secondAttrList = UpLeakageProtectorDLT6452007.this.identifyCodeDesc.secondValues;
                        attrList = UpLeakageProtectorDLT6452007.this.identifyCodeDesc.values;
                        TabName = DeviceTableEnum.Device_Rcd.getTableName();
                        CmdType = UpLeakageProtectorDLT6452007.this.identifyCodeDesc.cmdType;
                        tripEventRecord = UpLeakageProtectorDLT6452007.this.identifyCodeDesc.dataAnalyze.tripEventRecord;
                        controlWord = UpLeakageProtectorDLT6452007.this.identifyCodeDesc.dataAnalyze.controlWord;
                    }};
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }
}
