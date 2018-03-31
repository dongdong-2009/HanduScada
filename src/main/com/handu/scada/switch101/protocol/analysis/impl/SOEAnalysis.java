package main.com.handu.scada.switch101.protocol.analysis.impl;

import main.com.handu.scada.switch101.protocol.analysis.BaseAnalysis;
import main.com.handu.scada.switch101.protocol.bean.BaseData;
import main.com.handu.scada.switch101.protocol.bean.DataAttr;
import main.com.handu.scada.switch101.protocol.enums.Ti;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public class SOEAnalysis extends BaseAnalysis {

    public SOEAnalysis(BaseData baseData) {
        super(baseData);
    }

    @Override
    public BaseData analysis(BaseData baseData) {
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
                        }
                    }
                    startInfoAddress++;
                }
            }
            if (dataAttrs.size() > 0) {
                baseData.setDataAttrs(dataAttrs);
                return baseData;
            }
        }
        return null;
    }

    @Override
    protected DataAttr pointAnalysis(Object object) {
        if (pointLength == 10) {
            byte[] b = (byte[]) object;
            if (b != null) {
                int value = HexUtils.byteToInt(b[2]);
                DataAttr dataAttr = super.pointAnalysis(value);
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
        return null;
    }
}
