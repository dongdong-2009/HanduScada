package main.com.handu.scada.protocol101.faultRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 柳梦 on 2018/05/30.
 * 故障录波文件管理
 */
public class FaultRecordFileManager {

    /**
     * 保存录波文件名称信息
     */
    public static ConcurrentHashMap<String, List<FaultRecordFile>> map = new ConcurrentHashMap<>();

    private static FaultRecordFileManager singleton = null;

    public static FaultRecordFileManager getInstance() {
        if (singleton == null) {
            synchronized (FaultRecordFileManager.class) {
                if (singleton == null) {
                    singleton = new FaultRecordFileManager();
                }
            }
        }
        return singleton;
    }

    private FaultRecordFileManager() {
    }


    public void put(String deviceAddress, FaultRecordFile faultRecordFile) {
        if (faultRecordFile == null) return;
        synchronized (map) {
            if (map.containsKey(deviceAddress)) {
                List<FaultRecordFile> files = map.get(deviceAddress);
                if (files != null) {
                    files.add(faultRecordFile);
                }
            } else {
                List<FaultRecordFile> files = new ArrayList<>();
                files.add(faultRecordFile);
                map.put(deviceAddress, files);
            }
        }
    }

    /**
     * 开始读取指定的文件
     *
     * @param deviceAddress
     */
    public List<FaultRecordFile> getFaultRecordFiles(String deviceAddress) {
        return map.get(deviceAddress);
    }


    /**
     * 根据id号获取指定文件
     *
     * @param deviceAddress
     * @param id
     * @return
     */
    public FaultRecordFile getFaultRecordFile(String deviceAddress, byte id[]) {
        List<FaultRecordFile> files = map.get(deviceAddress);
        if (files != null) {
            Optional<FaultRecordFile> optional = files.stream().filter(e -> e.findFiledById(id)).findFirst();
            if (optional.isPresent()) return optional.get();
        }
        return null;
    }

    /**
     * 移除指定对象
     *
     * @param deviceAddress
     */
    public void remove(String deviceAddress) {
        map.remove(deviceAddress);
    }
}
