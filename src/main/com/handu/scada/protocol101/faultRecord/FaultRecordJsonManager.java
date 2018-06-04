package main.com.handu.scada.protocol101.faultRecord;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.utils.StringsUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2018/06/01.
 * 故障录波已读文件json文件管理
 */
public class FaultRecordJsonManager {

    private static final String FAULT_RECORD_FILE_FOLDER = "faultRecord" + File.separator;
    private static final String FILE_NAME = "readNames.json";
    private BlockingQueue<FaultRecordInfo> queue;
    private static FaultRecordJsonManager manager;

    public static FaultRecordJsonManager getInstance() {
        if (manager == null) {
            synchronized (FaultRecordJsonManager.class) {
                if (manager == null) {
                    manager = new FaultRecordJsonManager();
                }
            }
        }
        return manager;
    }

    private FaultRecordJsonManager() {
        queue = new LinkedBlockingQueue<>();
        Executors.newSingleThreadExecutor().execute(new WriteJson2FileRunnable());
    }

    private class WriteJson2FileRunnable implements Runnable {
        @Override
        public void run() {
            FaultRecordInfo info;
            while ((info = poll()) != null) {
                write(info);
            }
        }
    }

    /**
     * @param info
     */
    private synchronized void write(FaultRecordInfo info) {
        try {
            String path = FAULT_RECORD_FILE_FOLDER + info.getDeviceAddress() + File.separator + FILE_NAME;
            String content = read(path);
            JsonObject object;
            if (StringsUtils.isNotEmpty(content)) {
                JsonParser parser = new JsonParser();
                object = parser.parse(content).getAsJsonObject();
                info.getFiles()
                        .stream()
                        .filter(recordFile -> !object.has(recordFile.getName()))
                        .forEach(recordFile -> object.addProperty(recordFile.getName(), recordFile.getName()));
            } else {
                object = new JsonObject();
                for (FaultRecordFile recordFile : info.getFiles()) {
                    object.addProperty(recordFile.getName(), recordFile.getName());
                }
            }
            if (object != null) {
                // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
                FileWriter writer = new FileWriter(path, false);
                writer.write(object.toString());
                writer.close();
            }
        } catch (IOException e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * @param path
     * @return
     */
    private synchronized String read(String path) {
        try {
            File file = new File(path);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                boolean b = fileParent.mkdirs();
                if (!b) return null;
            }
            if (!file.exists()) {
                boolean b = file.createNewFile();
                if (!b) return null;
            }
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }

    /**
     * 根据设备地址找到已读录波文件的json文件，
     * 找到已读文件的名称列表
     *
     * @param fileNames
     * @param deviceAddress
     * @return
     */
    public List<String> getNotReadFileNames(String deviceAddress, List<String> fileNames) {
        String path = FAULT_RECORD_FILE_FOLDER + deviceAddress + File.separator + FILE_NAME;
        String content = read(path);
        if (StringsUtils.isNotEmpty(content)) {
            JsonParser parser = new JsonParser();
            JsonObject object = parser.parse(content).getAsJsonObject();
            List<String> names = object.entrySet()
                    .stream()
                    .map(e -> e.getValue().getAsString())
                    .collect(Collectors.toList());
            return fileNames
                    .stream()
                    .filter(e -> !names.contains(e))
                    .collect(Collectors.toList());
        }
        return null;
    }

    private FaultRecordInfo poll() {
        try {
            return this.queue.take();
        } catch (InterruptedException e) {
            return null;
        }
    }

    /**
     * @param files
     */
    private void put(String deviceAddress, List<FaultRecordFile> files) {
        FaultRecordInfo info = new FaultRecordInfo(deviceAddress, files);
        try {
            queue.put(info);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存读取到的录波文件的文件名称到json文件
     *
     * @param deviceAddress
     * @param file
     */
    public void saveFileName2JsonFile(String deviceAddress, FaultRecordFile file) {
        if (deviceAddress != null && file != null) {
            put(deviceAddress, new ArrayList<FaultRecordFile>() {{
                add(file);
            }});
        }
    }

    public void saveFileName2JsonFile(String deviceAddress, List<FaultRecordFile> files) {
        if (deviceAddress != null && files != null) {
            put(deviceAddress, files);
        }
    }
}
