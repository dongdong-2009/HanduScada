package main.com.handu.scada.protocol.protocol.DLT645.LP1997.impl;

import main.com.handu.scada.enums.DeviceTableEnum;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuUpParse;
import main.com.handu.scada.protocol.base.BaseDLT645;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.protocol.DLT645.LP1997.IdentifyCodeDesc;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

/**
 * Created by 柳梦 on 2018/04/24.
 */
@DtuUpParse
public class UpLeakageProtectorDLT6451997 extends BaseDLT645 {

    @Override
    public boolean valid(byte[] buff) {
        try {
            if (buff.length < 10) return false;
            //针头针尾校验
            if (buff[0] == 0x68 && buff[7] == 0x68 && buff[buff.length - 1] == 0x16) {
                byte blen = (byte) (buff[9] + 0x0C);
                if (blen != buff.length) return false;//长度校验
                byte cs = 0;
                int len = buff.length - 2;

                for (int i = 0; i < len; i++)//有10+Length个字节需要进行校验
                {
                    cs += buff[i];//透传的帧从第5个字节开始
                }
                byte controlCode = buff[8];
                //校验和,并且应答满足97协议
                return cs == buff[len] && (controlCode == (byte) 0x81
                        || controlCode == (byte) 0x84
                        || controlCode == (byte) 0xC1
                        || controlCode == (byte) 0xC4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void getAddress(byte[] buff) {
        if (buff.length >= 7) {
            ///报文结构解析
            this.AddressCode = new byte[6];
            System.arraycopy(buff, 1, this.AddressCode, 0, this.AddressCode.length);
            this.PostalAddress = HexUtils.byteArrayToHexStr(HexUtils.reverse(this.AddressCode));
        }
    }

    @Override
    public ProtocolLayerData parse(MediaData mediaData) {
        try {
            byte[] buff = mediaData.CommandData;
            if (buff == null) return null;
            if (buff.length < 10) return null;
            //去除前导字节
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
                this.IdentifyCode = new byte[2];
                System.arraycopy(buff, 10, this.IdentifyCode, 0, 2);
                //正常应答
                if (ControlCode == (byte) 0x81 || ControlCode == (byte) 0x84) {
                    this.Datas = new byte[this.DataLength - 2];
                    System.arraycopy(buff, 12, this.Datas, 0, this.Datas.length);
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
                            deviceTypeEnum = DeviceTypeEnum.LP1997;
                            CommandData = mediaData.CommandData;
                            CommandName = mediaData.MsgName;
                            DLT645Address = UpLeakageProtectorDLT6451997.this.AddressCode;
                            PostalAddress = UpLeakageProtectorDLT6451997.this.PostalAddress;
                            DTUString = mediaData.DTUString;
                            secondAttrList = UpLeakageProtectorDLT6451997.this.identifyCodeDesc.secondValues;
                            attrList = UpLeakageProtectorDLT6451997.this.identifyCodeDesc.values;
                            TabName = DeviceTableEnum.Device_Rcd.getTableName();
                            CmdType = UpLeakageProtectorDLT6451997.this.identifyCodeDesc.cmdType;
                            tripEventRecord = UpLeakageProtectorDLT6451997.this.identifyCodeDesc.dataAnalyze.tripEventRecord;
                            controlWord = UpLeakageProtectorDLT6451997.this.identifyCodeDesc.dataAnalyze.controlWord;
                        }};
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }
}
