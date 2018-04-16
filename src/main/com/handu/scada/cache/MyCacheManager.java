package main.com.handu.scada.cache;

import main.com.handu.scada.config.Config;
import main.com.handu.scada.db.bean.*;
import main.com.handu.scada.db.bean.common.DeviceDtuCacheResult;
import main.com.handu.scada.db.mapper.DeviceDtuMapper;
import main.com.handu.scada.db.mapper.DeviceRealRemotesignallingMapper;
import main.com.handu.scada.db.mapper.DeviceRealRemotetelemetryMapper;
import main.com.handu.scada.db.mapper.DeviceRemoteindexsMapper;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.LogUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2017/12/19.
 */
public class MyCacheManager implements ICacheManager {

    //试跳记录
    public static final String DEVICE_RCD_TRIAL_SWITCH_LOG = "DEVICE_RCD_TRIAL_SWITCH_LOG";
    private long start = 0;
    private long end = 0;
    private final ConcurrentHashMap<String, DeviceRemoteindexs> remoteIndexesMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, DeviceRealRemotetelemetry> deviceRealRemoteTelemeTriesMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, DeviceRealRemotesignalling> deviceRealRemoteSignallingsMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, DeviceDtuCacheResult> deviceDtuCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> trialswitchlogCache = new ConcurrentHashMap<>();

    private static MyCacheManager singleton;

    public static MyCacheManager getInstance() {
        if (singleton == null) {
            synchronized (MyCacheManager.class) {
                if (singleton == null) {
                    singleton = new MyCacheManager();
                }
            }
        }
        return singleton;
    }

    private MyCacheManager() {
    }

    private static ConcurrentHashMap<String, EntityCache> caches = new ConcurrentHashMap<>();

    /**
     * 存入缓存
     *
     * @param key
     * @param cache
     */
    public void putCache(String key, EntityCache cache) {
        if (!caches.containsKey(key))
            caches.put(key, cache);
    }

    /**
     * 存入缓存
     *
     * @param key
     * @param data
     */
    public void putCache(String key, Object data) {
        putCache(key, new EntityCache(data));
    }

    /**
     * 存入缓存
     *
     * @param key
     * @param data
     */
    public void putCache(String key, Object data, long timeOut) {
        timeOut = timeOut > 0 ? timeOut : 0L;
        putCache(key, new EntityCache(data, timeOut, System.currentTimeMillis()));
    }

    /**
     * 获取对应缓存
     *
     * @param key
     * @return
     */
    public EntityCache getCacheByKey(String key) {
        if (this.isContains(key)) {
            return caches.get(key);
        }
        return null;
    }

    @Override
    public Object getCacheDataByKey(String key) {
        return getDataByKey(key);
    }

    /**
     * 获取对应缓存
     *
     * @param key
     * @return
     */
    @Override
    public <T> T getDataByKey(String key) {
        if (this.isContains(key)) {
            return (T) caches.get(key).getData();
        }
        return null;
    }

    @Override
    public void refreshDataByKey(String key, Object data) {
        if (this.isContains(key)) {
            putCache(key, data);
        }
    }

    /**
     * 获取所有缓存
     *
     * @return
     */
    public Map<String, EntityCache> getCacheAll() {
        return caches;
    }

    /**
     * 判断是否在缓存中
     *
     * @param key
     * @return
     */
    public boolean isContains(String key) {
        return caches.containsKey(key);
    }

    /**
     * 清除所有缓存
     */
    public void clearAll() {
        caches.clear();
    }

    /**
     * 清除对应缓存
     *
     * @param key
     */
    public void clearByKey(String key) {
        if (this.isContains(key)) {
            caches.remove(key);
        }
    }

    /**
     * 缓存是否超时失效
     *
     * @param key
     * @return
     */
    public boolean isTimeOut(String key) {
        if (!caches.containsKey(key)) {
            return true;
        }
        EntityCache cache = caches.get(key);
        long timeOut = cache.getTimeOut();
        long lastRefreshTime = cache.getLastRefreshTime();
        return timeOut == 0 || System.currentTimeMillis() - lastRefreshTime >= timeOut;
    }

    /**
     * 获取所有key
     *
     * @return
     */
    public Set<String> getAllKeys() {
        return caches.keySet();
    }

    /**
     * 初始化所有缓存
     *
     * @return
     */
    public boolean init() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtil.getSqlSession();
            initDeviceAndDtu(sqlSession);
            updateDtuState(sqlSession);
            initDeviceRemoteIndexes(sqlSession);
            initDeviceRealData(sqlSession);
            //initDeviceRcdTrialSwitchLog();
            return true;
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
        return false;
    }

    /**
     * 将所有设备改成离线状态
     *
     * @param sqlSession
     */
    private void updateDtuState(SqlSession sqlSession) {
        start = System.currentTimeMillis();
        DeviceDtuMapper dtuMapper = sqlSession.getMapper(DeviceDtuMapper.class);
        String[] dtuPorts = Config.getDtuPorts().split(",");
        List<Integer> list = Arrays.stream(dtuPorts).map(Integer::parseInt).collect(Collectors.toList());
        if (list.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(" insert into device_staterecord(RecordId,DeviceTableName,DeviceId,state,description,UnLineTime) values ");
            final int[] i = {0};
            deviceDtuCache.entrySet().stream().filter(entry -> list.contains(entry.getValue().getPort())).forEach(entry -> {
                DeviceDtuCacheResult cacheResult = entry.getValue();
                if (i[0] != 0) sb.append(",");
                sb.append("('").append(cacheResult.getDtuId()).append("','device_dtu','").append(cacheResult.getDtuId()).append("',2,'已离线','").append(DateUtils.getNowSqlDateTime()).append("')");
                i[0]++;
            });
            sb.append(" on duplicate key update RecordId=values(RecordId),DeviceTableName=values(DeviceTableName),DeviceId=values(DeviceId),State=values(State),Description=values(Description),UnLineTime=values(UnLineTime)");
            if (i[0] > 0) {
                int result = dtuMapper.updateBySql(sb.toString());
                end = System.currentTimeMillis();
                LogUtils.error("init dtu state info ----->" + result + " take " + (end - start) + " ms", true);
            }
        }
    }

    /**
     * 缓存实时库数据
     *
     * @param sqlSession
     */
    private void initDeviceRealData(SqlSession sqlSession) {
        start = System.currentTimeMillis();
        DeviceRealRemotetelemetryMapper deviceRealRemotetelemetryMapper = sqlSession.getMapper(DeviceRealRemotetelemetryMapper.class);
        List<DeviceRealRemotetelemetry> records = deviceRealRemotetelemetryMapper.selectDeviceRealRemoteTelemetry(Arrays.asList(Config.getDtuPorts().split(",")));
        if (records != null && records.size() > 0) {
            for (DeviceRealRemotetelemetry record : records) {
                deviceRealRemoteTelemeTriesMap.put(record.getRemoteindexsid(), record);
            }
            end = System.currentTimeMillis();
            LogUtils.error("init device real remotetelemetry info ----->" + records.size() + " take " + (end - start) + " ms", true);
        }
        start = System.currentTimeMillis();
        DeviceRealRemotesignallingMapper deviceRealRemotesignallingMapper = sqlSession.getMapper(DeviceRealRemotesignallingMapper.class);
        List<DeviceRealRemotesignalling> deviceRealRemotesignallingList = deviceRealRemotesignallingMapper.selectDeviceRealRemoteSignalling(Arrays.asList(Config.getDtuPorts().split(",")));
        if (deviceRealRemotesignallingList != null && deviceRealRemotesignallingList.size() > 0) {
            for (DeviceRealRemotesignalling remotesignalling : deviceRealRemotesignallingList) {
                deviceRealRemoteSignallingsMap.put(remotesignalling.getRemoteindexsid(), remotesignalling);
            }
            end = System.currentTimeMillis();
            LogUtils.error("init device real remotesignalling info----->" + deviceRealRemotesignallingList.size() + " take " + (end - start) + " ms", true);
        }
    }

    /**
     * @param remoteindexsid
     * @return
     */
    public DeviceRealRemotetelemetry getDeviceRealRemotetelemetry(String remoteindexsid) {
        return deviceRealRemoteTelemeTriesMap.get(remoteindexsid);
    }

    /**
     * @param remoteindexsid
     * @param deviceRealRemotetelemetry
     */
    public void putDeviceRealRemotetelemetry(String remoteindexsid, DeviceRealRemotetelemetry deviceRealRemotetelemetry) {
        deviceRealRemoteTelemeTriesMap.put(remoteindexsid, deviceRealRemotetelemetry);
    }

    /**
     * @param remoteindexsid
     * @return
     */
    public DeviceRealRemotesignalling getDeviceRealRemotesignalling(String remoteindexsid) {
        return deviceRealRemoteSignallingsMap.get(remoteindexsid);
    }

    /**
     * @param remoteindexsid
     * @param deviceRealRemotesignalling
     */
    public void putDeviceRealRemotesignalling(String remoteindexsid, DeviceRealRemotesignalling deviceRealRemotesignalling) {
        deviceRealRemoteSignallingsMap.put(remoteindexsid, deviceRealRemotesignalling);
    }

    /**
     * 初始化设备和dtu
     *
     * @param sqlSession
     */
    private void initDeviceAndDtu(SqlSession sqlSession) {
        start = System.currentTimeMillis();
        //初始化设备相关
        CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
        List<DeviceDtuCacheResult> cacheResults = commonMapper.selectDeviceDtuCacheResult(Arrays.asList(Config.getDtuPorts().split(",")));
        if (cacheResults != null && cacheResults.size() > 0) {
            putDeviceDtuCacheResult(cacheResults);
            end = System.currentTimeMillis();
            LogUtils.error("init device dtu info ----->" + cacheResults.size() + " take " + (end - start) + " ms", true);
        }
    }

    /**
     * @param deviceTableName
     * @param dtuAddress
     * @param deviceAddress
     * @return
     */
    public DeviceDtuCacheResult getDeviceDtuCacheResult(String dtuAddress, String deviceTableName, String deviceAddress) {
        if (deviceDtuCache.containsKey(dtuAddress + deviceTableName.toLowerCase() + deviceAddress))
            return deviceDtuCache.get(dtuAddress + deviceTableName.toLowerCase() + deviceAddress);
        return null;
    }

    /**
     * @param cacheResults
     */
    public void putDeviceDtuCacheResult(List<DeviceDtuCacheResult> cacheResults) {
        synchronized (deviceDtuCache) {
            for (DeviceDtuCacheResult cacheResult : cacheResults) {
                deviceDtuCache.put(cacheResult.getDtuAddress() + cacheResult.getDeviceTableName().toLowerCase() + cacheResult.getDeviceAddress(), cacheResult);
            }
        }
    }

    /**
     * @return
     */
    public ConcurrentHashMap<String, DeviceDtuCacheResult> getDeviceDtuCache() {
        return deviceDtuCache;
    }

    /**
     * 初始化试跳记录
     */
    private void initDeviceRcdTrialSwitchLog() {
        putCache(DEVICE_RCD_TRIAL_SWITCH_LOG, trialswitchlogCache);
        LogUtils.error("init device rcd trial switch log ----->", true);
    }

    /**
     * 初始化设备索引表
     *
     * @param sqlSession
     */
    private void initDeviceRemoteIndexes(SqlSession sqlSession) {
        start = System.currentTimeMillis();
        DeviceRemoteindexsMapper mapper = sqlSession.getMapper(DeviceRemoteindexsMapper.class);
        List<DeviceRemoteindexs> list = mapper.selectDeviceRemoteIndexes(Arrays.asList(Config.getDtuPorts().split(",")));
        if (list != null) {
            String key;
            for (DeviceRemoteindexs remoteindexs : list) {
                key = remoteindexs.getDeviceid() + remoteindexs.getDevicetablename().toLowerCase() + remoteindexs.getDataitem();
                remoteIndexesMap.put(key, remoteindexs);
            }
            end = System.currentTimeMillis();
            LogUtils.error("init device remote indexes ----->" + list.size() + " take " + (end - start) + " ms", true);
        }
    }

    /**
     * @param deviceId
     * @param deviceTableName
     * @param dataItem
     * @return
     */
    public DeviceRemoteindexs getDeviceRemoteindexes(String deviceId, String deviceTableName, String dataItem) {
        if (remoteIndexesMap.containsKey(deviceId + deviceTableName + dataItem))
            return remoteIndexesMap.get(deviceId + deviceTableName + dataItem);
        return null;
    }

    /**
     * @param deviceId
     * @param deviceTableName
     * @param dataItem
     * @param item
     * @return
     */
    public void putDeviceRemoteindexes(String deviceId, String deviceTableName, String dataItem, DeviceRemoteindexs item) {
        remoteIndexesMap.put(deviceId + deviceTableName + dataItem, item);
    }
}
