package main.com.handu.scada.protocol101.device101.analysis.up.impl;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.server.protocol101.Protocol101CommandHandler;
import main.com.handu.scada.netty.server.protocol101.Protocol101CtxManager;
import main.com.handu.scada.protocol101.device101.analysis.up.BaseUpAnalysis;
import main.com.handu.scada.protocol101.protocol.bean.DataAttr;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.protocol101.protocol.enums.SOE;
import main.com.handu.scada.protocol101.protocol.enums.Ti;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public class SOEUpAnalysis extends BaseUpAnalysis {

    public SOEUpAnalysis(Protocol101Data baseData) {
        super(baseData);
    }

    @Override
    public Protocol101Data analysis() {
        try {
            if (init()) {
                if (SQ == 0) {
                    startInfoAddress = pointData[0] + pointData[1] * 256;
                    if (TI == Ti.M_ME_TE_1) {
                        pointLength = 10;
                    } else if (TI == Ti.M_ME_TF_1) {
                        pointLength = 10;
                    }
                    for (int i = 0; i < pointCount; i++) {
                        byte[] b = new byte[pointLength];
                        System.arraycopy(pointData, i * pointLength, b, 0, b.length);
                        if (pointLength == 10) {
                            //前两位点地址,第三位值,后面为时间
                            DataAttr dataAttr = pointAnalysis(b);
                            if (dataAttr != null) {
                                dataAttrs.add(dataAttr);
                            }
                        }
                        startInfoAddress++;
                    }
                } else if (SQ == 1) {
                    //获取起始地址
                    startInfoAddress = pointData[0] + pointData[1] * 256;
                    //获取数据信息域
                    byte[] pd = new byte[pointData.length - 2];
                    System.arraycopy(pointData, 2, pd, 0, pd.length);
                    pointData = pd;
                    //SOE事件
                    if (TI == Ti.M_ME_TE_1) {
                        pointLength = 8;
                    } else if (TI == Ti.M_ME_TF_1) {
                        pointLength = 8;
                    }
                    for (int i = 0; i < pointCount; i++) {
                        byte[] b = new byte[pointLength];
                        System.arraycopy(pointData, i * pointLength, b, 0, b.length);
                        if (pointLength == 8) {
                            DataAttr dataAttr = pointAnalysis(b);
                            if (dataAttr != null) {
                                dataAttrs.add(dataAttr);
                                //如果是录波闭锁就开始读取录波文件目录和文件名称
                                if (Objects.equals(dataAttr.getName(), SOE.FAULT_RECORD.getName())) {
                                    int value = Integer.parseInt(String.valueOf(dataAttr.getValue()));
                                    if (value == 1) {
                                        Protocol101CommandHandler handler = Protocol101CtxManager.getHandler(address);
                                        if (handler != null) {
                                            handler.readFaultFileCatalogue();
                                        }
                                    }
                                }
                            }
                        }
                        startInfoAddress++;
                    }
                }
                if (dataAttrs.size() > 0) {
                    protocol101Data.setDataAttrs(dataAttrs);
                    return protocol101Data;
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }

    @Override
    protected DataAttr pointAnalysis(Object object) {
        try {
            if (pointLength == 10) {
                byte[] b = (byte[]) object;
                if (b != null) {
                    int value = HexUtils.byteToInt(b[2]);
                    DataAttr dataAttr = super.pointAnalysis(value);
                    if (dataAttr != null) {
                        int year = HexUtils.byteToInt(b[9]) + 2000;
                        int month = HexUtils.byteToInt(b[8]);
                        int day = HexUtils.byteToInt(b[7]);
                        int h = HexUtils.byteToInt(b[6]);
                        int m = HexUtils.byteToInt(b[5]);
                        int s = HexUtils.byteToInt(b[4]);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day, h, m, s);
                        Date date = calendar.getTime();
                        dataAttr.setSoeTime(DateUtils.date2SqlDate(date));
                        return dataAttr;
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }
}
