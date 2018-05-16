package main.com.handu.scada.protocol.protocol.DLT645.LP2007.impl;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.IProtocol;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.base.SecondLpRecord;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.utils.Crc16Utils;
import main.com.handu.scada.utils.HexUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by 柳梦 on 2018/03/02.
 * 二级漏保档案创建
 */
public class SecondLpRecordCreate implements IProtocol {

    @Override
    public ProtocolLayerData parse(MediaData mediaData) {
        ProtocolLayerData protocolLayerData = null;
        try {
            byte[] buff = mediaData.CommandData;
            if (buff != null) {
                buff = HexUtils.cleanFEAndFF(buff);
                if (valid(buff)) {
                    protocolLayerData = new ProtocolLayerData();
                    String dtuAddress = mediaData.DTUString;
                    protocolLayerData.secondLpRecords = new ArrayList<>();
                    protocolLayerData.CmdType = DeviceCmdTypeEnum.SecondLpRecord;
                    protocolLayerData.DTUString = dtuAddress;
                    int count = buff[7];
                    //数据域长度：包括 5B FF LEN1 LEN2 04 SEQ1 SEQ2 LPCount Crc1 Crc12 FE
                    int dataLength = buff.length - 11;
                    byte[] data = new byte[dataLength];
                    System.arraycopy(buff, 8, data, 0, dataLength);
                    for (int i = 0; i < count; i++) {
                        byte[] d = new byte[26];
                        System.arraycopy(data, i * 26, d, 0, 26);
                        SecondLpRecord record = parse(dtuAddress, d);
                        if (record != null) {
                            protocolLayerData.secondLpRecords.add(record);
                        }
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return protocolLayerData;
    }

    private SecondLpRecord parse(String dtuAddress, byte[] data) {
        if (data.length != 26) return null;
        byte[] b = Arrays.copyOfRange(data, 0, 6);
        String lpAddress = HexUtils.byteArrayToHexStr(HexUtils.reverse(b));
        int baudRate = HexUtils.bcdByteToInt(data[6]);
        baudRate = baudRate == 0 ? 1200 : baudRate == 1 ? 2400 : 9600;
        int serialPort = HexUtils.bcdByteToInt(data[7]);
        int type = HexUtils.bcdByteToInt(data[8]);
        double ua = HexUtils.bcdByteToInt(data[9]) / 10d + HexUtils.bcdByteToInt(data[10]) * 10;
        double ub = HexUtils.bcdByteToInt(data[11]) / 10d + HexUtils.bcdByteToInt(data[12]) * 10;
        double uc = HexUtils.bcdByteToInt(data[13]) / 10d + HexUtils.bcdByteToInt(data[14]) * 10;
        double ia = HexUtils.bcdByteToInt(data[15]) / 10d + HexUtils.bcdByteToInt(data[16]) * 10 + HexUtils.bcdByteToInt(data[17]) * 1000;
        double ib = HexUtils.bcdByteToInt(data[18]) / 10d + HexUtils.bcdByteToInt(data[19]) * 10 + HexUtils.bcdByteToInt(data[20]) * 1000;
        double ic = HexUtils.bcdByteToInt(data[21]) / 10d + HexUtils.bcdByteToInt(data[22]) * 10 + HexUtils.bcdByteToInt(data[23]) * 1000;
        double io = HexUtils.bcdByteToInt(data[24]) + HexUtils.bcdByteToInt(data[25]) * 100d;
        int finalBaudRate = baudRate;
        return new SecondLpRecord() {{
            setLpAddress(lpAddress);
            setDtuAddress(dtuAddress);
            setBaudRate(finalBaudRate);
            setSerialPort(serialPort);
            setType(type);
            setRatedUa(ua);
            setRatedUb(ub);
            setRatedUc(uc);
            setRatedIa(ia);
            setRatedIb(ib);
            setRatedIc(ic);
            setRatedIo(io);
        }};
    }

    @Override
    public boolean valid(byte[] bytes) {
        try {
            if (bytes == null || bytes.length < 37 || bytes[4] != 0x04) return false;
            int arrLength = bytes.length;
            //计算校验位,这里只计算了数据域
            int crc = Crc16Utils.calcCrc16(bytes, 7, arrLength - 10);
            byte[] CS = new byte[2];
            CS[0] = (byte) (crc >> 8);
            CS[1] = (byte) (crc & 0xFF);
            byte b1 = bytes[arrLength - 3];
            byte b2 = bytes[arrLength - 2];
            return b1 == CS[0] && b2 == CS[1];
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return false;
    }
}
