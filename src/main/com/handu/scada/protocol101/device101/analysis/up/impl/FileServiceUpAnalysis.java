package main.com.handu.scada.protocol101.device101.analysis.up.impl;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.server.protocol101.Protocol101CommandHandler;
import main.com.handu.scada.netty.server.protocol101.Protocol101CtxManager;
import main.com.handu.scada.protocol101.device101.analysis.up.BaseUpAnalysis;
import main.com.handu.scada.protocol101.faultRecord.FaultRecordFile;
import main.com.handu.scada.protocol101.faultRecord.FaultRecordFileManager;
import main.com.handu.scada.protocol101.faultRecord.FaultRecordJsonManager;
import main.com.handu.scada.protocol101.faultRecord.FileCmdType;
import main.com.handu.scada.protocol101.protocol.bean.DataAttr;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.protocol101.protocol.enums.DataType;
import main.com.handu.scada.protocol101.protocol.enums.Protocol101CmdEnum;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by 柳梦 on 2018/05/30.
 * 故障指示器故障录波文件服务
 */
public class FileServiceUpAnalysis extends BaseUpAnalysis {

    public FileServiceUpAnalysis(Protocol101Data baseData) {
        super(baseData);
    }

    @Override
    public Protocol101Data analysis() {
        try {
            if (init()) {
                //操作标识
                byte cmdType = pointData[3];
                FileCmdType type = FileCmdType.getFileCmdType(cmdType);
                if (type != null) {
                    byte success;
                    switch (type) {
                        case READ_CATALOGUE:
                            //结果描述字 0成功 1失败
                            success = pointData[4];
                            if (success == 0x00) {
                                //后续标志 0无后续 1有后续
                                byte more = pointData[9];
                                //本帧文件数量
                                byte count = pointData[10];
                                if (count != 0x00) {
                                    //文件内容
                                    byte fileContents[] = new byte[pointData.length - 11];
                                    System.arraycopy(pointData, 11, fileContents, 0, fileContents.length);
                                    int index = 0;
                                    for (byte i = 0; i < count; i++) {
                                        byte fileNameLength = fileContents[index];
                                        byte b[] = new byte[fileNameLength + 13];
                                        System.arraycopy(fileContents, index, b, 0, b.length);
                                        FaultRecordFile file = analysisFileName(b);
                                        FaultRecordFileManager.getInstance().put(address, file);
                                        index = fileNameLength + 13;
                                    }
                                    //如果没有后续报文了就返回目录读取完毕
                                    if (more == 0x00) {
                                        //开始读取录波文件
                                        Protocol101CommandHandler handler = Protocol101CtxManager.getHandler(address);
                                        if (handler != null) {
                                            handler.readFaultRecordFile();
                                        }
                                    }
                                }
                            }
                            break;
                        //读文件激活确认
                        case READ_FILE_START_CONFIRM:
                            //结果描述字 0成功 1失败
                            success = pointData[4];
                            if (success == 0x00) {
                                byte fileLength = pointData[5];
                                byte fileNames[] = new byte[fileLength];
                                System.arraycopy(pointData, 6, fileNames, 0, fileLength);
                                String fileName = new String(fileNames);
                                byte id_size[] = new byte[8];
                                System.arraycopy(pointData, pointData.length - 8, id_size, 0, 8);
                                FaultRecordFile file = new FaultRecordFile(fileName,
                                        id_size[0], id_size[1], id_size[2], id_size[3],
                                        id_size[4], id_size[5], id_size[6], id_size[7]);
                                List<FaultRecordFile> files = FaultRecordFileManager.getInstance().getFaultRecordFiles(address);
                                if (files != null) {
                                    files.stream()
                                            .filter(e -> Objects.equals(e.getName(), file.getName()))
                                            .findFirst()
                                            .ifPresent(e -> {
                                                e.setId1(file.getId1());
                                                e.setId2(file.getId2());
                                                e.setId3(file.getId3());
                                                e.setId4(file.getId4());
                                                e.setSize1(file.getSize1());
                                                e.setSize2(file.getSize2());
                                                e.setSize3(file.getSize3());
                                                e.setSize4(file.getSize4());
                                            });
                                }
                            }
                            break;
                        //读文件数据传输
                        case READ_FILE_RESPONSE:
                            //id号
                            byte id[] = new byte[4];
                            id[0] = pointData[4];
                            id[1] = pointData[5];
                            id[2] = pointData[6];
                            id[3] = pointData[7];
                            //数据段号
                            byte dataNum[] = new byte[4];
                            dataNum[0] = pointData[8];
                            dataNum[1] = pointData[9];
                            dataNum[2] = pointData[10];
                            dataNum[3] = pointData[11];
                            //后续标志
                            byte more = pointData[12];
                            byte data[] = new byte[pointData.length - 14];
                            System.arraycopy(pointData, 13, data, 0, data.length);
                            byte b = 0x00;
                            for (byte aData : data) {
                                b += aData;
                            }
                            byte checkSum = pointData[pointData.length - 1];
                            if (checkSum == b) {
                                List<FaultRecordFile> files = FaultRecordFileManager.getInstance().getFaultRecordFiles(address);
                                files.stream()
                                        .filter(e -> e.findFiledById(id))
                                        .findFirst()
                                        .ifPresent(e -> {
                                            e.setDataNum(dataNum);
                                            e.setFile(data);
                                            e.setMore(more);
                                            e.setSize(data.length);
                                            Protocol101CommandHandler handler = Protocol101CtxManager.getHandler(address);
                                            if (handler != null) {
                                                handler.readFaultFileConfirm(e);
                                            }
                                        });
                            }
                            //无后续
                            if (more == 0x00) {
                                FaultRecordFile file = FaultRecordFileManager.getInstance().getFaultRecordFile(address, id);
                                if (file != null) {
                                    if (file.isSuccess()) {
                                        dataAttrs.add(new DataAttr() {{
                                            setName("录波文件录取成功");
                                            setValue(file);
                                            setRecordTime(DateUtils.getNowSqlDateTime());
                                            setDataType(DataType.FILE);
                                        }});
                                        protocol101Data.setDataAttrs(dataAttrs);
                                        protocol101Data.setCmdType(Protocol101CmdEnum.READ_FILE_SUCCESS);
                                        FaultRecordJsonManager.getInstance().saveFileName2JsonFile(address, file);
                                        return protocol101Data;
                                    }
                                }
                            }
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }

    /**
     * 解析文件名称和时间
     */
    private FaultRecordFile analysisFileName(byte[] bytes) {
        byte fileNameLength = bytes[0];
        byte b[] = new byte[fileNameLength];
        System.arraycopy(bytes, 1, b, 0, b.length);
        String fileName = new String(b);
        b = new byte[7];
        System.arraycopy(bytes, bytes.length - 7, b, 0, b.length);
        int year = HexUtils.byteToInt(b[6]) + 2000;
        int month = HexUtils.byteToInt(b[5]);
        int day = HexUtils.byteToInt(b[4]);
        int h = HexUtils.byteToInt(b[3]);
        int m = HexUtils.byteToInt(b[2]);
        int s = HexUtils.byteToInt(b[1]);
        //int ss = HexUtils.byteToInt(b[0]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, h, m, s);
        Date date = calendar.getTime();
        FaultRecordFile recordFile = new FaultRecordFile();
        recordFile.setDate(DateUtils.dateToStr(date));
        recordFile.setName(fileName);
        return recordFile;
    }
}
