package main.com.handu.scada.utils;


import main.com.handu.scada.netty.server.dtu.DtuCmdHandler;

import java.util.Date;
import java.util.Map;

import static main.com.handu.scada.config.Config.isDebug;

/**
 * Created by 柳梦 on 2017/12/27.
 */
public class LogUtils {

    public static void error(Object msg) {
        error(msg, isDebug);
    }

    public static void error(Object... msg) {
        String s = "";
        for (Object aMsg : msg) {
            if (!s.equals("")) {
                s = s + ",";
            }
            s += aMsg;
        }
        error(s, isDebug);
    }

    public static void info(Object msg) {
        info(msg, isDebug);
    }

    public static void error(Object msg, boolean isDebug) {
        if (isDebug) {
            System.err.println(DateUtils.dateToStr(new Date()) + "--" + Thread.currentThread().getName() + "--" + msg);
            sendToClient(String.valueOf(msg));
        }
    }

    public static void info(Object msg, boolean isDebug) {
        if (isDebug) {
            System.out.println(DateUtils.dateToStr(new Date()) + "--" + Thread.currentThread().getName() + "--" + msg);
            sendToClient(String.valueOf(msg));
        }
    }

    /**
     * 打印并存储
     *
     * @param msg
     */
    public static void sql(String msg) {
        System.err.println(DateUtils.dateToStr(new Date()) + "--" + Thread.currentThread().getName() + "--" + msg);
        TxtUtils.getInstance().sql(msg);
    }

    private static void sendToClient(String msg) {
        if (!DtuCmdHandler.clientMap.isEmpty()) {
            for (Map.Entry<String, DtuCmdHandler> entry : DtuCmdHandler.clientMap.entrySet()) {
                DtuCmdHandler handler = entry.getValue();
                if (handler != null) {
                    handler.sendCommand(handler.getChannelHandlerContext(), msg.getBytes());
                }
            }
        }
    }

    /**
     * 打印任务执行
     *
     * @param msg
     */
    public static void printTask(String msg) {
        error(msg);
        TxtUtils.getInstance().task(msg);
    }
}
