package main.com.handu.scada.cache;

import main.com.handu.scada.config.Config;
import main.com.handu.scada.db.bean.DeviceRealRemotesignalling;
import main.com.handu.scada.db.bean.DeviceRealRemotetelemetry;
import main.com.handu.scada.db.bean.DeviceRemoteindexs;
import main.com.handu.scada.db.bean.common.DeviceDtuCacheResult;
import main.com.handu.scada.db.mapper.DeviceRealRemotesignallingMapper;
import main.com.handu.scada.db.mapper.DeviceRealRemotetelemetryMapper;
import main.com.handu.scada.db.mapper.DeviceRemoteindexsMapper;
import main.com.handu.scada.db.mapper.DeviceStaterecordMapper;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.utils.LogUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 柳梦 on 2017/12/19.
 */
public class MyCacheManager implements ICacheManager {

    //设备索引
    public static final String DEVICE_REMOTE_INDEXES = "DEVICE_REMOTE_INDEXES";
    //dtu,设备信息
    public static final String DEVICE_DTU_INFO = "DEVICE_DTU_INFO";
    //设备实时遥测数据
    public static final String DEVICE_REAL_REMOTE_TELEMETRY = "DEVICE_REAL_REMOTE_TELEMETRY";
    //设备实时遥信数据
    public static final String DEVICE_REAL_REMOTE_SIGNALLING = "DEVICE_REAL_REMOTE_SIGNALLING";
    //试跳记录
    public static final String DEVICE_RCD_TRIAL_SWITCH_LOG = "DEVICE_RCD_TRIAL_SWITCH_LOG";

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
            updateDtuState(sqlSession);
            initDeviceAndDtu(sqlSession);
            initDeviceRemoteIndexes(sqlSession);
            initDeviceRealData(sqlSession);
            initDeviceRcdTrialSwitchLog();
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
        DeviceStaterecordMapper mapper = sqlSession.getMapper(DeviceStaterecordMapper.class);
        int result = mapper.updateDeviceStateRecord(Arrays.asList(Config.getDtuPorts().split(",")), 2, "服务器重启，已离线");
        if (result > 0) {
            LogUtils.error("init dtu state info ----->" + result, true);
        }
    }

    /**
     * 缓存实时库数据
     *
     * @param sqlSession
     */
    private void initDeviceRealData(SqlSession sqlSession) {
        DeviceRealRemotetelemetryMapper deviceRealRemotetelemetryMapper = sqlSession.getMapper(DeviceRealRemotetelemetryMapper.class);
        List<DeviceRealRemotetelemetry> records = deviceRealRemotetelemetryMapper.selectDeviceRealRemoteTelemetry(Arrays.asList(Config.getDtuPorts().split(",")));
        Vector<DeviceRealRemotetelemetry> results = new Vector<>();
        if (records != null && records.size() > 0) {
            results.addAll(records);
        }
        putCache(DEVICE_REAL_REMOTE_TELEMETRY, results);
        LogUtils.error("init device real remotetelemetry info ----->" + results.size(), true);

        DeviceRealRemotesignallingMapper deviceRealRemotesignallingMapper = sqlSession.getMapper(DeviceRealRemotesignallingMapper.class);
        List<DeviceRealRemotesignalling> deviceRealRemotesignallingList = deviceRealRemotesignallingMapper.selectDeviceRealRemoteSignalling(Arrays.asList(Config.getDtuPorts().split(",")));
        Vector<DeviceRealRemotesignalling> deviceRealRemotesignallingVector = new Vector<>();
        if (deviceRealRemotesignallingList != null && deviceRealRemotesignallingList.size() > 0) {
            deviceRealRemotesignallingVector.addAll(deviceRealRemotesignallingList);
        }
        putCache(DEVICE_REAL_REMOTE_SIGNALLING, deviceRealRemotesignallingVector);
        LogUtils.error("init device real remotesignalling info----->" + deviceRealRemotesignallingVector.size(), true);
    }

    /**
     * 初始化设备和dtu
     *
     * @param sqlSession
     */
    private void initDeviceAndDtu(SqlSession sqlSession) {
        //初始化设备相关
        CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
        List<DeviceDtuCacheResult> cacheResults = commonMapper.selectDeviceDtuCacheResult(Arrays.asList(Config.getDtuPorts().split(",")));
        Vector<DeviceDtuCacheResult> deviceRcdResults = new Vector<>();
        if (cacheResults != null && cacheResults.size() > 0) {
            deviceRcdResults.addAll(cacheResults);
        }
        putCache(DEVICE_DTU_INFO, deviceRcdResults);
        LogUtils.error("init device dtu info ----->" + deviceRcdResults.size(), true);
    }

    /**
     * 初始化试跳记录
     */
    private void initDeviceRcdTrialSwitchLog() {
        ConcurrentHashMap<String, String> trialswitchlogCache = new ConcurrentHashMap<>();
        putCache(DEVICE_RCD_TRIAL_SWITCH_LOG, trialswitchlogCache);
        LogUtils.error("init device rcd trial switch log ----->", true);
    }

    /**
     * 初始化漏保索引表
     *
     * @param sqlSession
     */
    private void initDeviceRemoteIndexes(SqlSession sqlSession) {
        DeviceRemoteindexsMapper mapper = sqlSession.getMapper(DeviceRemoteindexsMapper.class);
        List<DeviceRemoteindexs> deviceRemoteindexsList = mapper.selectDeviceRemoteIndexes(Arrays.asList(Config.getDtuPorts().split(",")));
        Vector<DeviceRemoteindexs> deviceRemoteindexsVector = new Vector<>();
        if (deviceRemoteindexsList != null && deviceRemoteindexsList.size() > 0) {
            deviceRemoteindexsVector.addAll(deviceRemoteindexsList);
        }
        putCache(DEVICE_REMOTE_INDEXES, deviceRemoteindexsVector);
        LogUtils.error("init device remote indexes ----->" + deviceRemoteindexsVector.size(), true);
    }
}
