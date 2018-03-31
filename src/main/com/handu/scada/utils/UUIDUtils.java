package main.com.handu.scada.utils;

import java.util.UUID;

/**
 * Created by 柳梦 on 2017/12/20.
 */
public class UUIDUtils {

    //获得全球唯一性的id
    public static String getUUId() {
        //生成的id---942cd30b-16c8-449e-8dc5-028f38495bb5--中间含有横杠，
        return UUID.randomUUID().toString();
    }
}
