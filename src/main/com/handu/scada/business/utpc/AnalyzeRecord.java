package main.com.handu.scada.business.utpc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 柳梦 on 2018/04/28.
 * 分析记录
 */
public class AnalyzeRecord {

    public static String UTPC = "UTPC";

    public static String OVERLOAD = "overload";

    /**
     * 记录三项不平衡和低电压
     */
    private static final ConcurrentHashMap<String, AnalyzeRecordModel> analyzeMap = new ConcurrentHashMap<>();

    //三相不平衡标准率
    public static double UTPC_RATE = 0.25;
    //标准电压
    public static double NORMAL_VOLTAGE = 220;
    //低电压率
    public static double LOW_VOLTAGE_RATE = 0.1;
    //重载60%
    public static double OVERLOAD60 = 0.6;


    private static AnalyzeRecord ourInstance = new AnalyzeRecord();

    public static AnalyzeRecord getInstance() {
        return ourInstance;
    }

    private AnalyzeRecord() {
    }

    public static boolean containsKey(String key) {
        return analyzeMap.containsKey(key);
    }

    public static void put(String key, AnalyzeRecordModel model) {
        synchronized (analyzeMap) {
            if (!analyzeMap.containsKey(key)) {
                analyzeMap.put(key, model);
            }
        }
    }

    public static AnalyzeRecordModel get(String key) {
        return analyzeMap.get(key);
    }

    public static void remove(String key) {
        synchronized (analyzeMap) {
            if (analyzeMap.containsKey(key)) {
                analyzeMap.remove(key);
            }
        }
    }
}
