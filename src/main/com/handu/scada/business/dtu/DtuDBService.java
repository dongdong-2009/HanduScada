package main.com.handu.scada.business.dtu;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.db.bean.DeviceHistoryRemotesignalling;
import main.com.handu.scada.db.bean.DeviceHistoryRemotetelemetry;
import main.com.handu.scada.db.bean.DeviceRealRemotesignalling;
import main.com.handu.scada.db.bean.DeviceRealRemotetelemetry;
import main.com.handu.scada.db.bean.common.DeviceDtuCacheResult;
import main.com.handu.scada.db.mapper.common.CommonMapper;
import main.com.handu.scada.db.utils.MyBatisUtil;
import main.com.handu.scada.enums.TableEnum;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.utils.StringsUtils;
import main.com.handu.scada.utils.UUIDUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2018/04/03.
 * dtu相关数据入库
 */
public class DtuDBService {

    /**
     * 每次取数据的数量
     */
    private int takeSize = 10000;
    /**
     * 提交间隔时间
     */
    private int interval = 5000;


    /**
     * DTU状态更改队列
     */
    private BlockingQueue<DtuStateResult> dtuStateQueue = new LinkedBlockingDeque<>();

    /**
     * 设备数据遥信遥测入库队列
     */
    private BlockingQueue<DeviceData> dataQueue = new LinkedBlockingDeque<>();

    private static DtuDBService singleton;

    public static DtuDBService getInstance() {
        if (singleton == null) {
            synchronized (DtuDBService.class) {
                if (singleton == null) {
                    singleton = new DtuDBService();
                }
            }
        }
        return singleton;
    }

    private DtuDBService() {
        init();
        //提交间隔时间
        new Timer().schedule(new ChangeDtuStateTsk(), 0, interval);
        new Timer().schedule(new DeviceDataTask(), 0, interval);
    }

    private void init() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("configure.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                if (entry.getKey().equals("db.takeSize")) {
                    takeSize = Integer.valueOf(String.valueOf(entry.getValue()));
                } else if (entry.getKey().equals("db.interval")) {
                    interval = Integer.parseInt(String.valueOf(entry.getValue())) * 1000;
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * 消息入队
     *
     * @param result
     * @return
     */
    public void push(DtuStateResult result) {
        try {
            this.dtuStateQueue.put(result);
        } catch (InterruptedException e) {
            ExceptionHandler.print(e);
        }
    }

    /**
     * 消息入队
     *
     * @param result
     * @return
     */
    public void push(DeviceData result) {
        try {
            this.dataQueue.put(result);
        } catch (InterruptedException e) {
            ExceptionHandler.print(e);
        }
    }

    /**
     * 消息出队
     *
     * @return
     */
    private List<DtuStateResult> take() {
        List<DtuStateResult> results = new ArrayList<>();
        this.dtuStateQueue.drainTo(results, takeSize);
        return results;
    }

    /**
     * 消息出队
     *
     * @return
     */
    private List<DeviceData> take1() {
        List<DeviceData> results = new ArrayList<>();
        this.dataQueue.drainTo(results, takeSize);
        return results;
    }

    /**
     * 根据dtuId获取deviceId
     *
     * @param dtuAddress
     * @return
     */
    private String getDeviceIdByDtuAddress(String dtuAddress) {
        if (StringsUtils.isEmpty(dtuAddress)) return null;
        ConcurrentHashMap<String, DeviceDtuCacheResult> map = MyCacheManager.getInstance().getDeviceDtuCache();
        synchronized (map) {
            Optional<Map.Entry<String, DeviceDtuCacheResult>> optional = map.entrySet()
                    .stream()
                    .filter(entry -> Objects.equals(entry.getValue().getDtuAddress(), dtuAddress))
                    .findFirst();
            if (optional.isPresent()) {
                return optional.get().getValue().getDtuId();
            }
        }
        return null;
    }

    private class ChangeDtuStateTsk extends TimerTask {

        @Override
        public void run() {
            SqlSession sqlSession = null;
            try {
                List<DtuStateResult> results = take().stream().map(result -> {
                    if (StringsUtils.isEmpty(result.getDtuId())) {
                        result.setDtuId(getDeviceIdByDtuAddress(result.getDtuAddress()));
                    }
                    return result;
                }).collect(Collectors.toList());
                if (results.size() > 0) {
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(" insert into device_staterecord(RecordId,DeviceTableName,DeviceId,State,Description,OnlineTime) values ");
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(" insert into device_staterecord(RecordId,DeviceTableName,DeviceId,State,Description,UnLineTime) values ");
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(" insert into device_history_staterecord(RecordId,DeviceTableName,DeviceId,State,Description,UnLineTime) values");
                    final int[] i = {0, 0};
                    results.stream().filter(result -> !StringsUtils.isEmpty(result.getDtuId())).forEach(result -> {
                        if (result.getState() == DtuState.ONLINE) {
                            if (i[0] != 0) sb1.append(",");
                            sb1.append("('")
                                    .append(result.getDtuId())
                                    .append("','")
                                    .append(TableEnum.Device_Dtu.getTableName().toLowerCase())
                                    .append("','")
                                    .append(result.getDtuId())
                                    .append("',1,'在线','")
                                    .append(result.getTime())
                                    .append("')");
                            i[0]++;
                        } else if (result.getState() == DtuState.OFFLINE) {
                            if (i[1] != 0) {
                                sb2.append(",");
                                sb3.append(",");
                            }
                            sb2.append("('")
                                    .append(result.getDtuId())
                                    .append("','")
                                    .append(TableEnum.Device_Dtu.getTableName().toLowerCase())
                                    .append("','")
                                    .append(result.getDtuId())
                                    .append("',2,'离线','")
                                    .append(result.getTime())
                                    .append("')");
                            sb3.append("('")
                                    .append(UUIDUtils.getUUId())
                                    .append("','")
                                    .append(TableEnum.Device_Dtu.getTableName().toLowerCase())
                                    .append("','")
                                    .append(result.getDtuId())
                                    .append("',2,'离线','")
                                    .append(result.getTime())
                                    .append("')");
                            i[1]++;
                        }
                    });

                    sqlSession = MyBatisUtil.getSqlSession();
                    CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
                    if (i[0] > 0) {
                        sb1.append(" on duplicate key update RecordId=values(RecordId),DeviceTableName=values(DeviceTableName),DeviceId=values(DeviceId),State=values(State),Description=values(Description),OnlineTime=values(OnlineTime)");
                        mapper.updateBySql(sb1.toString());
                    }
                    if (i[1] > 0) {
                        sb2.append(" on duplicate key update RecordId=values(RecordId),DeviceTableName=values(DeviceTableName),DeviceId=values(DeviceId),State=values(State),Description=values(Description),UnLineTime=values(UnLineTime)");
                        mapper.updateBySql(sb2.toString());
                        mapper.insertBySql(sb3.toString());
                    }
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            } finally {
                if (sqlSession != null) sqlSession.close();
            }
        }
    }

    private class DeviceDataTask extends TimerTask {
        @Override
        public void run() {
            SqlSession sqlSession = null;
            try {
                List<DeviceData> results = take1();
                if (results.size() > 0) {
                    //遥信历史库
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(" insert into device_history_remotesignalling (RemoteSignallingId, RecordTime, RemoteIndexsId, Value, Description ) values ");
                    //遥测历史库
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(" insert into device_history_remotetelemetry (RemoteTelemetryId, RecordTime, RemoteIndexsId, Value, Description ) values ");
                    //遥信实时库
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(" insert into device_real_remotesignalling (RemoteSignallingId, RemoteIndexsId, Value, RecordTime, Description ) values ");
                    //遥测实时库
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(" insert into device_real_remotetelemetry (RemoteTelemetryId, RemoteIndexsId, Value, RecordTime, Description ) values ");
                    final int[] i = {0, 0, 0, 0};
                    for (DeviceData result : results) {
                        switch (result.getDataType()) {
                            case YX_HISTORY:
                                if (i[0] != 0) sb1.append(",");
                                DeviceHistoryRemotesignalling deviceHistoryRemotesignalling = (DeviceHistoryRemotesignalling) result.getData();
                                if (deviceHistoryRemotesignalling != null) {
                                    sb1.append("(")
                                            .append("'")
                                            .append(deviceHistoryRemotesignalling.getRemotesignallingid())
                                            .append("',")
                                            .append("'")
                                            .append(deviceHistoryRemotesignalling.getRecordtime())
                                            .append("',")
                                            .append("'")
                                            .append(deviceHistoryRemotesignalling.getRemoteindexsid())
                                            .append("',")
                                            .append(deviceHistoryRemotesignalling.getValue())
                                            .append(",")
                                            .append("'")
                                            .append(deviceHistoryRemotesignalling.getDescription() == null ? "" : deviceHistoryRemotesignalling.getDescription())
                                            .append("'")
                                            .append(")");
                                    i[0]++;
                                }
                                break;
                            case YC_HISTORY:
                                if (i[1] != 0) sb2.append(",");
                                DeviceHistoryRemotetelemetry deviceHistoryRemotetelemetry = (DeviceHistoryRemotetelemetry) result.getData();
                                if (deviceHistoryRemotetelemetry != null) {
                                    sb2.append("(")
                                            .append("'")
                                            .append(deviceHistoryRemotetelemetry.getRemotetelemetryid())
                                            .append("',")
                                            .append("'")
                                            .append(deviceHistoryRemotetelemetry.getRecordtime())
                                            .append("',")
                                            .append("'")
                                            .append(deviceHistoryRemotetelemetry.getRemoteindexsid())
                                            .append("','")
                                            .append(deviceHistoryRemotetelemetry.getValue())
                                            .append("',")
                                            .append("'")
                                            .append(deviceHistoryRemotetelemetry.getDescription() == null ? "" : deviceHistoryRemotetelemetry.getDescription())
                                            .append("'")
                                            .append(")");
                                    i[1]++;
                                }
                                break;
                            case YX_REAL:
                                if (i[2] != 0) sb3.append(",");
                                DeviceRealRemotesignalling remotesignalling = (DeviceRealRemotesignalling) result.getData();
                                if (remotesignalling != null) {
                                    sb3.append("('")
                                            .append(remotesignalling.getRemotesignallingid())
                                            .append("','")
                                            .append(remotesignalling.getRemoteindexsid())
                                            .append("','")
                                            .append(remotesignalling.getValue())
                                            .append("','")
                                            .append(remotesignalling.getRecordtime())
                                            .append("','")
                                            .append(remotesignalling.getDescription() == null ? "" : remotesignalling.getDescription())
                                            .append("')");
                                    i[2]++;
                                }
                                break;
                            case YC_REAL:
                                if (i[3] != 0) sb4.append(",");
                                DeviceRealRemotetelemetry remotetelemetry = (DeviceRealRemotetelemetry) result.getData();
                                if (remotetelemetry != null) {
                                    sb4.append("('")
                                            .append(remotetelemetry.getRemotetelemetryid())
                                            .append("','")
                                            .append(remotetelemetry.getRemoteindexsid())
                                            .append("','")
                                            .append(remotetelemetry.getValue())
                                            .append("','")
                                            .append(remotetelemetry.getRecordtime())
                                            .append("','")
                                            .append(remotetelemetry.getDescription() == null ? "" : remotetelemetry.getDescription())
                                            .append("')");
                                    i[3]++;
                                }
                                break;
                            case UTPC:
                                break;
                            case LOW_VOLTAGE:
                                break;
                            case READ_DEVICE_MODEL:
                                break;
                            case ALARM:
                                break;
                        }
                    }


                    sqlSession = MyBatisUtil.getSqlSession();
                    CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
                    if (i[0] > 0) {
                        mapper.insertBySql(sb1.toString());
                    }
                    if (i[1] > 0) {
                        mapper.insertBySql(sb2.toString());
                    }
                    if (i[2] > 0) {
                        sb3.append(" on duplicate key update RemoteSignallingId=values(RemoteSignallingId), RemoteIndexsId=values(RemoteIndexsId), Value=values(Value), RecordTime=values(RecordTime), Description=values(Description)");
                        mapper.updateBySql(sb3.toString());
                    }
                    if (i[3] > 0) {
                        sb4.append(" on duplicate key update RemoteTelemetryId=values(RemoteTelemetryId), RemoteIndexsId=values(RemoteIndexsId), Value=values(Value), RecordTime=values(RecordTime), Description=values(Description)");
                        mapper.updateBySql(sb4.toString());
                    }
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            } finally {
                if (sqlSession != null) sqlSession.close();
            }
        }
    }
}
