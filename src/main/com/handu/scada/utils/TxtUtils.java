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

import static main.com.handu.scada.log.LogType.*;

/**
 * Created by 柳梦 on 2018/01/15.
 */
public class TxtUtils {

    private static TxtUtils singleton;

    private TxtUtils() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new PollLog());
    }

    /**
     * 懒加载
     *
     * @return
     */
    public static TxtUtils getInstance() {
        if (singleton == null) {
            synchronized (TxtUtils.class) {
                if (singleton == null) {
                    singleton = new TxtUtils();
                }
            }
        }
        return singleton;
    }

    private class PollLog implements Runnable {
        @Override
        public void run() {
            Log log;
            while ((log = LogQueue.getInstance().poll()) != null) {
                writeLogFile(log, log.getLogType() != EXPORT_DTU_ONLINE);
            }
        }

        /**
         * 写入文件
         *
         * @param log
         */
        private void writeLogFile(Log log, boolean append) {
            FileWriter writer = null;
            try {
                //我们按照每分钟创建文件夹，减少日志的整体大小，方便阅读
                String filePath = log.getPath() +
                        DateUtils.getFileTimeStr(new Date(), "yyyy-MM-dd-HH-mm") + File.separator +
                        log.getName() + log.getSuffix();

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
                writer = new FileWriter(filePath, append);
                writer.write(log.getContent());
            } catch (IOException e) {
                ExceptionHandler.handle(e);
            } finally {
                try {
                    if (writer != null) writer.close();
                } catch (IOException e) {
                    ExceptionHandler.handle(e);
                }
                if (log.getLogType() == EXPORT_DTU_ONLINE) {
                    LogUtils.info("export dtu online list to txt success...", true);
                }
            }
        }
    }

    public void error(String content) {
        if (StringsUtils.isEmpty(content)) return;
        LogQueue.getInstance().push(new Log(content, LogType.ERROR));
    }

    /**
     * 更新错误
     *
     * @param content
     */
    public void updateError(String content) {
        if (StringsUtils.isEmpty(content)) return;
        Log log = new Log(content, UPDATE_ERROR);
        LogQueue.getInstance().push(log);
    }

    /**
     * 更新成功
     *
     * @param content
     */
    public void updateSuccess(String content) {
        if (StringsUtils.isEmpty(content)) return;
        Log log = new Log(content, UPDATE_SUCCESS);
        LogQueue.getInstance().push(log);
    }

    public void info(String content) {
        if (StringsUtils.isEmpty(content)) return;
        LogQueue.getInstance().push(new Log(content, LogType.INFO));
    }

    public void sql(String content) {
        if (StringsUtils.isEmpty(content)) return;
        LogQueue.getInstance().push(new Log(content, LogType.SQL));
    }

    public void alarm(String content) {
        if (StringsUtils.isEmpty(content)) return;
        LogQueue.getInstance().push(new Log(content, LogType.ALARM));
    }

    public void exportDtuOnline(String content) {
        if (StringsUtils.isEmpty(content)) return;
        LogUtils.info("start export dtu online list to txt,please wait...", true);
        LogQueue.getInstance().push(new Log(content, LogType.EXPORT_DTU_ONLINE));
    }
}
