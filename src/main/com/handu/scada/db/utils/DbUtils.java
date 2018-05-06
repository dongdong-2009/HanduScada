package main.com.handu.scada.db.utils;

/**
 * Created by 柳梦 on 2018/04/27.
 * 数据库sql工具类
 */
public class DbUtils {

    /**
     * 判断如果是字符串就加入单引号
     *
     * @param str
     * @return
     */
    private static String getSqlStr(String str) {
        return str == null ? null : "'" + str + "'";
    }

    /**
     * 根据传入的sql字段类型返回sql字段
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String getColumn(T t) {
        if (t instanceof String) {
            return getSqlStr((String) t) + ",";
        }
        return t + ",";
    }

    /**
     * 根据传入的sql字段类型返回sql字段
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String getStartColumn(T t) {
        if (t instanceof String) {
            return "(" + getSqlStr((String) t) + ",";
        }
        return "(" + t + ",";
    }

    /**
     * 根据传入的sql字段类型返回sql字段
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String getEndColumn(T t) {
        if (t instanceof String) {
            return getSqlStr((String) t) + ")";
        }
        return t + ")";
    }
}
