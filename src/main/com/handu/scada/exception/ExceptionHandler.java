package main.com.handu.scada.exception;

import main.com.handu.scada.config.Config;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.TxtUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by 柳梦 on 2018/01/15.
 * 异常信息处理类
 */
public class ExceptionHandler {

    private static StringWriter stringWriter;
    private static PrintWriter printWriter;

    static {
        init();
    }

    /**
     * 初始化
     */
    private static void init() {
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
    }

    /**
     * 处理异常
     *
     * @param e
     */
    public static void handle(Throwable e) {
        e.printStackTrace(printWriter);
        String exception = stringWriter.toString();
        TxtUtils.getInstance().error(exception);
        if (Config.isDebug) LogUtils.error(exception);
    }

    /**
     * 打印错误日志
     *
     * @param e
     */
    public static void print(Throwable e) {
        if (Config.isDebug) e.printStackTrace();
    }
}
