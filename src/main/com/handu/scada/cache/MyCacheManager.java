package main.com.handu.scada.cache;

import main.com.handu.scada.config.Config;
import main.com.handu.scada.db.bean.common.AdditionProperty;
import main.com.handu.scada.db.bean.common.Device101CacheResult;
import main.com.handu.scada.db.bean.common.DeviceCacheResult;
import main.com.handu.scada.db.bean.common.DtuCacheResult;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.utils.DBServiceUtil;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.enums.DeviceGroup;
import main.com.handu.scada.enums.DeviceTableEnum;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2017/12/19.
 */
public class MyCacheManager extends DBServiceUtil implements ICacheManager {

    private long start = 0;
    private long end = 0;
    private final static ConcurrentHashMap<String, DtuCacheResult> dtuCacheResultMap = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<String, DeviceCacheResult> deviceCacheResultMap = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<String, Device101CacheResult> device101CacheResultMap = new ConcurrentHashMap<>();

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
            initDtuInfo(sqlSession);
            initDeviceInfo(sqlSession);
            updateDtuState(sqlSession);
            initDevice101Info(sqlSession);
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
        CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
        String[] dtuPorts = Config.getDtuPorts().split(",");
        List<Integer> list = Arrays
                .stream(dtuPorts)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        if (list.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(" insert into device_staterecord(RecordId,DeviceTableName,DeviceId,state,description) values ");
            final int[] i = {0};
            dtuCacheResultMap
                    .entrySet()
                    .stream()
                    .filter(entry -> list.contains(entry.getValue().getDtuPort()))
                    .forEach(entry -> {
                        DtuCacheResult cacheResult = entry.getValue();
                        if (i[0] != 0) sb.append(",");
                        sb.append(getStartColumn(cacheResult.getDtuAddress()))
                                .append(getColumn("device_dtu"))
                                .append(getColumn(cacheResult.getDtuAddress()))
                                .append(getColumn(2))
                                .append(getEndColumn("离线"));
                        i[0]++;
                    });
            sb.append(" on duplicate key update RecordId=values(RecordId)," +
                    "DeviceTableName=values(DeviceTableName),DeviceId=values(DeviceId),State=values(State)," +
                    "Description=values(Description)");
            if (i[0] > 0) {
                commonMapper.updateBySql(sb.toString());
                end = System.currentTimeMillis();
                LogUtils.error("init dtu state info ----->" + i[0] + " take " + (end - start) + " ms", true);
            }
        }
    }

    /**
     * 根据 设备地址 dtu地址 设备类型 获取缓存
     *
     * @param deviceTableName
     * @param dtuAddress
     * @param deviceAddress
     * @return
     */
    public static DeviceCacheResult getDeviceCacheResult(String dtuAddress, String deviceTableName, String deviceAddress) {
        return deviceCacheResultMap.get(dtuAddress + deviceTableName.toLowerCase() + deviceAddress);
    }

    /**
     * @param dtuAddress
     * @return
     */
    public static DtuCacheResult getDtuCacheResult(String dtuAddress) {
        return dtuCacheResultMap.get(dtuAddress);
    }

    /**
     * 获取dtu在线状态
     *
     * @param dtuAddress
     * @return
     */
    public static boolean isDtuOnline(String dtuAddress) {
        return getDtuCacheResult(dtuAddress) != null && getDtuCacheResult(dtuAddress).isDtuIsOnline();
    }

    /**
     * 根据dtu地址更新缓存在线状态
     *
     * @param dtuAddress dtu地址
     * @param isOnline   是否在线
     */
    public static void updateDtuOnlineState(String dtuAddress, boolean isOnline) {
        synchronized (dtuCacheResultMap) {
            DtuCacheResult result = dtuCacheResultMap.get(dtuAddress);
            if (result != null) result.setDtuIsOnline(isOnline);
        }
    }

    /**
     * 更新DTU缓存
     *
     * @param cacheResults
     */
    public static void updateDtuCacheResult(List<DtuCacheResult> cacheResults) {
        if (cacheResults != null) {
            synchronized (dtuCacheResultMap) {
                for (DtuCacheResult cacheResult : cacheResults) {
                    if (cacheResult != null) {
                        switch (cacheResult.getCmdType()) {
                            case DELETE:
                                break;
                            default:
                                dtuCacheResultMap.put(cacheResult.getDtuAddress(), cacheResult);
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据dtuId移除dtu缓存
     *
     * @param dtuId
     */
    public static void removeDtuCacheByDtuId(String dtuId) {
        synchronized (dtuCacheResultMap) {
            dtuCacheResultMap
                    .entrySet()
                    .stream()
                    .filter(e -> Objects.equals(e.getValue().getDtuId(), dtuId))
                    .map(Map.Entry::getValue)
                    .forEach(e -> deviceCacheResultMap.remove(e.getDtuAddress()));
        }
    }

    /**
     * 更新设备缓存
     *
     * @param cacheResults
     */
    public static void updateDeviceCacheResult(List<DeviceCacheResult> cacheResults) {
        if (cacheResults != null) {
            synchronized (deviceCacheResultMap) {
                for (DeviceCacheResult cacheResult : cacheResults) {
                    if (cacheResult != null) {
                        switch (cacheResult.getCmdType()) {
                            case DELETE:
                                break;
                            default:
                                deviceCacheResultMap.put(cacheResult.getDtuAddress() + cacheResult.getDeviceTableName().toLowerCase() + cacheResult.getDeviceAddress(), cacheResult);
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据设备id移除设备缓存
     *
     * @param deviceId
     */
    public static void removeDeviceCacheByDeviceId(String deviceId) {
        synchronized (deviceCacheResultMap) {
            deviceCacheResultMap
                    .entrySet()
                    .stream()
                    .filter(e -> Objects.equals(e.getValue().getDeviceId(), deviceId))
                    .map(Map.Entry::getValue)
                    .forEach(e -> deviceCacheResultMap.remove(e.getDtuAddress() + e.getDeviceTableName().toLowerCase() + e.getDeviceAddress()));
        }
    }

    /**
     * 获取设备缓存
     *
     * @return
     */
    public static ConcurrentHashMap<String, DeviceCacheResult> getDeviceCacheMap() {
        return deviceCacheResultMap;
    }

    /**
     * 获取dtu缓存
     *
     * @return
     */
    public static ConcurrentHashMap<String, DtuCacheResult> getDtuCacheMap() {
        return dtuCacheResultMap;
    }

    /**
     * 初始化dtu信息
     *
     * @param sqlSession
     */
    private void initDtuInfo(SqlSession sqlSession) {
        start = System.currentTimeMillis();
        //初始化设备相关
        CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
        List<DtuCacheResult> dtuCacheResults = commonMapper.selectDtuCacheResult(Arrays.asList(Config.getDtuPorts().split(",")), null);
        if (dtuCacheResults != null && dtuCacheResults.size() > 0) {
            synchronized (dtuCacheResultMap) {
                dtuCacheResults
                        .stream()
                        .filter(e -> StringsUtils.isNotEmpty(e.getDtuAddress()))
                        .forEach(dtuCacheResult -> dtuCacheResultMap.put(dtuCacheResult.getDtuAddress(), dtuCacheResult));
                end = System.currentTimeMillis();
                LogUtils.error("init dtu info ----->" + dtuCacheResultMap.size() + " take " + (end - start) + " ms", true);
            }
        }
    }

    /**
     * 初始化设备信息
     *
     * @param sqlSession
     */
    private void initDeviceInfo(SqlSession sqlSession) {
        start = System.currentTimeMillis();
        //初始化设备相关
        CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
        List<DeviceCacheResult> deviceCacheResults = commonMapper.selectDeviceCacheResult(Arrays.asList(Config.getDtuPorts().split(",")), null);
        if (deviceCacheResults != null && deviceCacheResults.size() > 0) {
            deviceCacheResults = initAdditionProperty(deviceCacheResults, commonMapper);
            synchronized (deviceCacheResultMap) {
                deviceCacheResults
                        .stream()
                        .filter(e -> StringsUtils.isNotEmpty(e.getDeviceAddress()))
                        .forEach(deviceCacheResult -> deviceCacheResultMap.put(deviceCacheResult.getDtuAddress() + deviceCacheResult.getDeviceTableName().toLowerCase() + deviceCacheResult.getDeviceAddress(), deviceCacheResult));
                end = System.currentTimeMillis();
                LogUtils.error("init device info ----->" + deviceCacheResultMap.size() + " take " + (end - start) + " ms", true);
            }
        }
    }

    /**
     * 初始化附属信息
     *
     * @param deviceCacheResults
     * @param commonMapper
     */
    public List<DeviceCacheResult> initAdditionProperty(List<DeviceCacheResult> deviceCacheResults, CommonMapper commonMapper) {
        List<DeviceTableEnum> tableEnums = DeviceTableEnum.getDeviceByGroup(DeviceGroup.DTU_DEVICE);
        tableEnums
                .stream()
                .filter(tableEnum -> tableEnum != null)
                .forEach(tableEnum -> {
                    List<DeviceCacheResult> deviceList = deviceCacheResults
                            .stream()
                            .filter(e -> Objects.equals(e.getDeviceTableName(), tableEnum.getTableName()))
                            .collect(Collectors.toList());
                    if (deviceList.size() > 0) {
                        StringBuilder builder = new StringBuilder();
                        String sql = tableEnum.getSqlAdditionProperty();
                        if (StringsUtils.isNotEmpty(sql)) {
                            builder.append(tableEnum.getSqlAdditionProperty());
                            deviceList.forEach(s -> builder.append(getColumn(s.getDeviceId())));
                            builder.deleteCharAt(builder.lastIndexOf(",")).append(")");
                            List<AdditionProperty> additionProperties = commonMapper.selectDeviceAdditionProperty(builder.toString());
                            if (additionProperties != null) {
                                Map<String, List<AdditionProperty>> map = additionProperties
                                        .stream()
                                        .collect(Collectors.groupingBy(AdditionProperty::getDeviceId, Collectors.toList()));
                                deviceList.forEach(e -> {
                                    String deviceId = e.getDeviceId();
                                    List<AdditionProperty> a = map.get(deviceId);
                                    if (a != null) {
                                        e.setAdditionProperties(a);
                                    }
                                });
                            }
                        }
                    }
                });
        return deviceCacheResults;
    }

    /**
     * 初始化101设备缓存
     *
     * @param sqlSession
     */
    private void initDevice101Info(SqlSession sqlSession) {
        start = System.currentTimeMillis();
        //初始化设备相关
        CommonMapper commonMapper = sqlSession.getMapper(CommonMapper.class);
        List<Device101CacheResult> device101CacheResults = commonMapper.selectDevice101CacheResult(Arrays.asList(Config.getSwitchPorts().split(",")), null);
        if (device101CacheResults != null && device101CacheResults.size() > 0) {
            synchronized (device101CacheResultMap) {
                device101CacheResults
                        .stream()
                        .filter(e -> StringsUtils.isNotEmpty(e.getDeviceAddress()))
                        .forEach(e -> device101CacheResultMap.put(e.getDeviceAddress(), e));
                end = System.currentTimeMillis();
                LogUtils.error("init device101 info ----->" + device101CacheResultMap.size() + " take " + (end - start) + " ms", true);
            }
        }
    }

    /**
     * 获取device101设备缓存
     *
     * @return
     */
    public static ConcurrentHashMap<String, Device101CacheResult> getDevice101CacheMap() {
        return device101CacheResultMap;
    }
}
