package main.com.handu.scada.switch101.protocol.analysis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.switch101.json.PointJsonData;
import main.com.handu.scada.switch101.json.Value;
import main.com.handu.scada.switch101.protocol.bean.BaseData;
import main.com.handu.scada.switch101.protocol.bean.DataAttr;
import main.com.handu.scada.switch101.protocol.enums.COT;
import main.com.handu.scada.switch101.protocol.enums.DataType;
import main.com.handu.scada.switch101.protocol.enums.Ti;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public abstract class BaseAnalysis implements IAnalysis {

    private static final String PATH = "pointJson" + File.separator;
    private Gson gson;
    protected List<DataAttr> dataAttrs;
    private BaseData baseData;
    //整个报文
    private byte[] commandData;
    //设备地址
    protected String address;
    //终端类型
    private String terminalCode;
    //数据类型
    private DataType dataType;
    //起始位置
    protected int startInfoAddress;
    //信息对象数据域
    protected byte[] pointData;
    //信息对象的数目
    protected int pointCount = 0;
    //信息对象数据长度
    protected int pointLength = 0;
    //数据标识位
    protected Ti TI;
    //可变结构限定词
    protected byte VSQ;
    //单个或者顺序
    protected int SQ = 1;
    //传送原因
    protected COT cot;
    //终端类型对应的json文件内容
    private List<PointJsonData> pointJsonDataList;

    public BaseAnalysis(BaseData baseData) {
        this.baseData = baseData;
        gson = new Gson();
        dataAttrs = new ArrayList<>();
        commandData = baseData.getCommandData();
        TI = baseData.getTI();
        dataType = baseData.getDataType();
    }

    /**
     * 根据data获取地址
     *
     * @return
     */
    protected boolean init() {
        if (commandData != null) {
            if (dataType != null) {
                byte[] address = new byte[2];
                System.arraycopy(commandData, 11, address, 0, 2);
                //地址高低位
                int low = HexUtils.byteToInt(address[0]);
                int high = HexUtils.byteToInt(address[1]) * 256;
                this.address = String.valueOf(low + high);
                baseData.setAddress(this.address);
                //vsq
                VSQ = commandData[8];
                //将vsq与10000000与运算获得最高位
                //vsq最高位为-1 则SQ=1，最高位为0 则SQ=0
                SQ = (VSQ & 0x80) == 0 ? 0 : 1;
                //vsq D0-D7 标识有几个信息对象
                //将vsq与0111 1111做与运算计算信息对象个数
                pointCount = VSQ & 0x7f;
                //传送原因
                cot = COT.getCOT(HexUtils.byteToInt(commandData[9]) + HexUtils.intToByte(commandData[10]) * 256);
                //信息对象数据域
                pointData = new byte[commandData.length - 15];
                //从第13位开始复制
                System.arraycopy(commandData, 13, pointData, 0, pointData.length);
                //获取终端类型
                terminalCode = getTerminalCode();
                if (!StringsUtils.isEmpty(terminalCode)) {
                    baseData.setTerminalCode(terminalCode);
                    pointJsonDataList = getPointJsonDataList();
                } else {
                    LogUtils.error("not find terminal code!");
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 根据地址获取终端类型
     *
     * @return
     */
    private String getTerminalCode() {
        if (address != null) {
            return "HY101";
        }
        return null;
    }

    /**
     * 获取json文件点位
     *
     * @return
     */
    private List<PointJsonData> getPointJsonDataList() {
        if (dataType != null && terminalCode != null) {
            String path = PATH + terminalCode + File.separator + dataType.getJsonName();
            File file = new File(path);
            if (file.exists()) {
                try {
                    String content = FileUtils.readFileToString(file, "UTF-8");
                    if (!StringsUtils.isEmpty(content)) {
                        return gson.fromJson(content, new TypeToken<List<PointJsonData>>() {
                        }.getType());
                    }
                } catch (Exception e) {
                    ExceptionHandler.handle(e);
                }
            } else {
                LogUtils.error("not find " + path + "!", true);
            }
        } else {
            LogUtils.error("not find dataType!");
        }
        return null;
    }

    /**
     * 点表解析
     */
    protected DataAttr pointAnalysis(Object value) {
        if (pointJsonDataList != null) {
            DataAttr dataAttr = new DataAttr();
            pointJsonDataList.stream().filter(pointJsonData -> pointJsonData.getValue().getInfoAddress() == startInfoAddress).findFirst().ifPresent(pointJsonData -> {
                Value v = pointJsonData.getValue();
                dataAttr.setName(v.getItemName());
                dataAttr.setValue(value);
                dataAttr.setUnit(v.getItemUnit());
                dataAttr.setRecordTime(DateUtils.getNowSqlDateTime());
            });
            return dataAttr;
        }
        return null;
    }
}
