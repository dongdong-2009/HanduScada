package main.com.handu.scada.protocol101.device101.analysis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol101.json.PointJsonData;
import main.com.handu.scada.protocol101.json.Value;
import main.com.handu.scada.protocol101.protocol.bean.DataAttr;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101BaseData;
import main.com.handu.scada.protocol101.protocol.enums.COT;
import main.com.handu.scada.protocol101.protocol.enums.DataType;
import main.com.handu.scada.protocol101.protocol.enums.Protocol101CmdEnum;
import main.com.handu.scada.protocol101.protocol.enums.Ti;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.StringsUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public abstract class BaseUpAnalysis implements IAnalysis {

    private static final String PATH = "pointJson" + File.separator;
    private Gson gson = new Gson();
    protected List<DataAttr> dataAttrs = new ArrayList<>();
    protected Protocol101BaseData baseData;
    //整个报文
    private byte[] commandData;
    //设备地址
    protected String address;
    //终端类型名称
    private String terminalName;
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

    public BaseUpAnalysis(Protocol101BaseData baseData) {
        this.baseData = baseData;
        //设置数据解析返回操作类型
        this.baseData.setCmdType(Protocol101CmdEnum.ALL_CALL);
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
        if (!isAnalysis()) return false;
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
                terminalName = getTerminalName();
                if (!StringsUtils.isEmpty(terminalName)) {
                    baseData.setTerminalCode(terminalName);
                    pointJsonDataList = getPointJsonDataList();
                    if (pointJsonDataList != null) {
                        baseData.setPointJsonDataList(pointJsonDataList);
                    }
                } else {
                    ExceptionHandler.handle(new Throwable("not find terminal code and pointJson file,can not analysis point data!"));
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 根据地址获取终端类型名称
     *
     * @return
     */
    private String getTerminalName() {
        if (address != null) {
            return "HC_FaultIndicator";
        }
        return null;
    }

    /**
     * 获取json文件点位
     *
     * @return
     */
    private List<PointJsonData> getPointJsonDataList() {
        if (dataType != null && terminalName != null) {
            String path = PATH + terminalName + File.separator + dataType.getJsonName();
            File file = new File(path);
            if (file.exists()) {
                try {
                    String content = FileUtils.readFileToString(file, "UTF-8");
                    if (StringsUtils.isNotEmpty(content)) {
                        return gson.fromJson(content, new TypeToken<List<PointJsonData>>() {
                        }.getType());
                    }
                } catch (Exception e) {
                    ExceptionHandler.handle(e);
                }
            } else {
                ExceptionHandler.handle(new Exception("not find " + path + "!"));
            }
        }
        return null;
    }

    /**
     * 点表解析
     */
    protected DataAttr pointAnalysis(Object value) {
        DataAttr dataAttr = null;
        if (pointJsonDataList != null) {
            Optional<PointJsonData> optional = pointJsonDataList
                    .stream()
                    .filter(pointJsonData -> pointJsonData.getValue().getInfoAddress() == startInfoAddress)
                    .findFirst();
            if (optional.isPresent()) {
                PointJsonData pointJsonData = optional.get();
                dataAttr = new DataAttr();
                Value v = pointJsonData.getValue();
                dataAttr.setPointPosition(startInfoAddress);
                dataAttr.setName(v.getItemName());
                dataAttr.setValue(value);
                dataAttr.setUnit(v.getItemUnit());
                dataAttr.setDataType(dataType);
                dataAttr.setRecordTime(DateUtils.getNowSqlDateTime());
            }
        }
        return dataAttr;
    }
}
