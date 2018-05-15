package main.com.handu.scada.db.utils;

import main.com.handu.scada.utils.LogUtils;

/**
 * Created by 柳梦 on 2018/05/02.
 */
public class DBServiceUtil {

    protected <T> String getColumn(T t) {
        return DbUtils.getColumn(t);
    }

    protected <T> String getStartColumn(T t) {
        return DbUtils.getStartColumn(t);
    }

    protected <T> String getEndColumn(T t) {
        return DbUtils.getEndColumn(t);
    }

    protected String getSql(StringBuilder sb) {
        if (sb != null) {
            String sql = sb.toString();
            LogUtils.info(sql);
            return sql;
        }
        return "select 1";
    }
}
