package main.com.handu.scada.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 柳梦 on 2017/12/02.
 */
public class Utils {

    /**
     * @param obj
     * @return
     */
    public static String bean2Json(Object obj) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }

    /**
     * @param jsonStr
     * @param objClass
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonStr, objClass);
    }

    /**
     * @param jsonStr
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String jsonStr, Type type) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonStr, type);
    }

    /**
     * @param msg
     */
    public static void logError(Object msg) {
        Logger logger = Logger.getLogger(Utils.class);
        logger.error("HanDu---" + msg);
    }

    /**
     * @param msg
     */
    public static void logInfo(Object msg) {
        Logger logger = Logger.getLogger(Utils.class);
        logger.info("HanDu---" + msg);
    }

    /**
     * @param map
     * @return
     */
    public static String mapToJson(Map map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    /**
     * @param list
     * @return
     */
    public static String listToJson(List list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    /**
     * 将map里面的key值更换为全部小写
     *
     * @param map
     * @return
     */
    public static Map<String, Object> mapKeyToLowerCase(Map<String, Object> map) {
        if (map != null && map.entrySet().size() > 0) {
            Map<String, Object> objectMap = new HashMap<String, Object>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey().toLowerCase();
                Object value = entry.getValue();
                objectMap.put(key, value);
            }
            return objectMap;
        }
        return null;
    }

    /**
     * 将list里面的map的key替换为小写
     *
     * @param mapList
     * @return
     */
    public static List<Map<String, Object>> listMapKeyToLowerCase(List<Map<String, Object>> mapList) {
        if (mapList != null && mapList.size() > 0) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> objectMap;
            for (Map<String, Object> map : mapList) {
                objectMap = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey().toLowerCase();
                    Object value = entry.getValue();
                    objectMap.put(key, value);
                }
                list.add(objectMap);
            }
            return list;
        }
        return null;
    }
}
