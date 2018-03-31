package main.com.handu.scada.utils;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.log.Log;
import main.com.handu.scada.log.LogQueue;
import main.com.handu.scada.log.LogType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static main.com.handu.scada.log.LogType.UPDATE_ERROR;
import static main.com.handu.scada.log.LogType.UPDATE_SUCCESS;

/**
 * Created by 柳梦 on 2018/01/15.
 */
public class TxtUtils {

    private static ExecutorService service = Executors.newCachedThreadPool();

    static {
        start();
    }

    public static void start() {
        service.execute(new PollLog());
    }

    private static class PollLog implements Runnable {
        @Override
        public void run() {
            while (true) {
                Log log = LogQueue.getInstance().poll();
                if (log != null) {
                    writeLogFile(log);
                }
            }
        }

        /**
         * 写入文件
         *
         * @param log
         */
        private void writeLogFile(Log log) {
            FileWriter writer = null;
            try {
                String filePath = log.getPath() + DateUtils.getDateStr(new Date()) + File.separator + log.getName() + log.getSuffix();
                File file = new File(filePath);
                File fileParent = file.getParentFile();
                if (!fileParent.exists()) {
                    boolean b = fileParent.mkdirs();
                    if (!b) return;
                }
                if (!file.exists()) {
                    boolean b = file.createNewFile();
                    if (!b) return;
                }
                // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
                writer = new FileWriter(filePath, true);
                writer.write(log.getContent());
            } catch (IOException e) {
                ExceptionHandler.handle(e);
            } finally {
                try {
                    if (writer != null) writer.close();
                } catch (IOException e) {
                    ExceptionHandler.handle(e);
                }
            }
        }
    }

    public static void error(String content) {
        LogQueue.getInstance().push(new Log(content, LogType.ERROR));
    }

    /**
     * 更新错误
     *
     * @param content
     */
    public static void updateError(String content) {
        Log log = new Log(content, UPDATE_ERROR);
        LogQueue.getInstance().push(log);
    }

    /**
     * 更新成功
     *
     * @param content
     */
    public static void updateSuccess(String content) {
        Log log = new Log(content, UPDATE_SUCCESS);
        LogQueue.getInstance().push(log);
    }

    public static void info(String content) {
        LogQueue.getInstance().push(new Log(content, LogType.INFO));
    }

    public static void alarm(String content) {
        LogQueue.getInstance().push(new Log(content, LogType.ALARM));
    }
}
