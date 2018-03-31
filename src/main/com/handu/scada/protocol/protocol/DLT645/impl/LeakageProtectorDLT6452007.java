package main.com.handu.scada.protocol.protocol.DLT645.impl;

import main.com.handu.scada.enums.TableEnum;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuUpParse;
import main.com.handu.scada.protocol.base.BaseDLT645;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.protocol.DLT645.IdentifyCodeDesc;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.StringsUtils;

/**
 * Created by 柳梦 on 2017/12/24.
 */

@DtuUpParse
public class LeakageProtectorDLT6452007 extends BaseDLT645 {

    private LeakageProtectorDLT6452007() {
    }

    @Override
    public void getAddress(byte[] tempBuff) {
        if (tempBuff != null) {
            byte[] buff = HexUtils.cleanFE(tempBuff);
            if (buff != null && buff.length >= 7) {
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
            if (buff.length <= 14) return false;
            byte cs = 0;
            int len = buff.length - 2;
            byte blen = (byte) (buff[9] + (byte) 0x0c);
            if (blen != buff.length) return false;//长度校验
            //针头针尾校验，
            if (buff[0] == 0x68 && buff[7] == 0x68 && buff[len + 1] == 0x16) {
                for (int i = 0; i < len; i++)//有10+Length个字节需要进行校验
                {
                    cs += buff[i];//透传的帧从第5个字节开始
                }
                //校验和
                return cs == buff[len];
            } else {
                return false;
            }
        } catch (Exception e) {
            ExceptionHandler.print(e);
        }
        return false;
    }

    @Override
    public MediaData sendCommand(ProtocolLayerData protocolLayerData) {
        return null;
    }

    @Override
    public ProtocolLayerData parse(MediaData mediaData) {
        try {
            byte[] buff = mediaData.CommandData;
            if (buff == null) return null;
            //漏报保温以0xFE开头0x16结束
            if (buff[0] == (byte) 0xFE && buff[buff.length - 1] == 0x16) {
                buff = HexUtils.cleanFE(buff);
                if (valid(buff)) {
                    dtuAddress = mediaData.DTUString;
                    getAddress(buff);
                    ///报文结构解析
                    this.AddressCode = new byte[6];
                    System.arraycopy(buff, 1, this.AddressCode, 0, this.AddressCode.length);
                    this.ControlCode = buff[8];
                    this.DataLength = buff[9];
                    this.CheckCode = buff[buff.length - 2];
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
                    ///数据域解析
                    this.identifyCodeDesc = new IdentifyCodeDesc();
                    if (identifyCodeDesc.isCorrectAnswer(this.ControlCode)) {
                        this.identifyCodeDesc.Dicode = this.IdentifyCode;
                        this.identifyCodeDesc.data = this.Datas;
                        this.identifyCodeDesc.sourcedata = buff;
                        this.identifyCodeDesc.address = this.PostalAddress;
                        this.identifyCodeDesc.dtime = DateUtils.getNowSqlDateTime();
                        this.IsSuccess = identifyCodeDesc.parse();
                    }
                    if (IsSuccess) {
                        protocolLayerData = new ProtocolLayerData() {{
                            deviceTypeEnum = DeviceTypeEnum.LP;
                            CommandData = mediaData.CommandData;
                            CommandName = mediaData.MsgName;
                            DLT645Address = LeakageProtectorDLT6452007.this.AddressCode;
                            PostalAddress = LeakageProtectorDLT6452007.this.PostalAddress;
                            DTUString = mediaData.DTUString;
                            secondAttrList = LeakageProtectorDLT6452007.this.identifyCodeDesc.secondValues;
                            attrList = LeakageProtectorDLT6452007.this.identifyCodeDesc.values;
                            TabName = TableEnum.Device_Rcd.getTableName();
                            CmdType = LeakageProtectorDLT6452007.this.identifyCodeDesc.cmdtype;
                            tripEventRecord = LeakageProtectorDLT6452007.this.identifyCodeDesc.dataanalyze.tripEventRecord;
                            controlWord = LeakageProtectorDLT6452007.this.identifyCodeDesc.dataanalyze.controlWord;
                        }};
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler.print(e);
        }
        return protocolLayerData;
    }
}
